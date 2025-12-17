package io.github.vihanmy.pgyappupload.pages

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.JDOMUtil
import com.intellij.util.xmlb.XmlSerializer
import io.github.vihanmy.pgyappupload.core.Tool
import io.github.vihanmy.pgyappupload.dialog.ProJectProvidableCompositionLocal
import io.github.vihanmy.pgyappupload.model.PluginSettingsStateComponent
import io.github.vihanmy.pgyappupload.model.uistate.CmdConfigUiState
import io.github.vihanmy.pgyappupload.model.uistate.PackageProcessConfigUiState
import io.github.vihanmy.pgyappupload.model.uistate.PluginSettingsUiState
import io.github.vihanmy.pgyappupload.tool.FileTool
import io.github.vihanmy.pgyappupload.ui.UIPackageProcessConfig
import org.jdom.input.SAXBuilder
import java.io.StringReader

@Preview
@Composable
fun PagePluginSettingPreView() {
    PagePluginSetting()
}

/**
 *# page : 设置页面
 * ```
 * author :Vihanmy
 * date   :2025-11-09 12:28
 * desc   :
 * ```
 */
@Composable
fun PagePluginSetting(
    initialSetting: PluginSettingsUiState = PluginSettingsUiState(),
) {
    val project = ProJectProvidableCompositionLocal.current

    fun chooseFileToImport(project: Project) {
        try {
            val targetFile = Tool.chooseConfigFile4Import(project) ?: return
            val xmlText = targetFile.inputStream.bufferedReader().use { it.readText() }

            val element = SAXBuilder()
                .build(StringReader(xmlText))
                .rootElement

            val importedState = XmlSerializer.deserialize(
                element,
                PluginSettingsStateComponent::class.java
            )
            PluginSettingsStateComponent.instance.loadState(importedState)
            initialSetting.updateFromStateComponent(PluginSettingsStateComponent.instance)
        } catch (e: Exception) {

        }
    }

    fun addCmd(item: PackageProcessConfigUiState) {
        item.cmdList.add(CmdConfigUiState())
    }

    fun removeCmd(item: PackageProcessConfigUiState, cmd: CmdConfigUiState) {
        item.cmdList.remove(cmd)
    }

    fun addConfig() {
        initialSetting.packageProcessConfigList.add(PackageProcessConfigUiState().apply {
            cmdList = mutableStateListOf(CmdConfigUiState())
        })
    }

    fun removeConfig(dat: PackageProcessConfigUiState) {
        initialSetting.packageProcessConfigList.remove(dat)
    }

    fun copyConfig(dat: PackageProcessConfigUiState) {
        val index = initialSetting.packageProcessConfigList.indexOf(dat)
        initialSetting.packageProcessConfigList.add(index + 1, dat.copy())
    }

    fun onCopyCmd(packageProcessConfig: PackageProcessConfigUiState, dat: CmdConfigUiState) {
        val index = packageProcessConfig.cmdList.indexOf(dat)
        packageProcessConfig.cmdList.add(index + 1, dat.copy())
    }

    Column(Modifier.wrapContentHeight().fillMaxWidth()) {
        Text(
            "蒲公英平台的 API KEY",
            color = MaterialTheme.colors.onSecondary
        )

        BasicTextField(
            value = initialSetting.pgyApiKey,
            onValueChange = {
                initialSetting.pgyApiKey = it
            },
            modifier = Modifier
                .border(1.dp, Color.Black)
                .padding(10.dp),
        )

        for (packageProcessConfig in initialSetting.packageProcessConfigList) {
            UIPackageProcessConfig(
                packageProcessConfig,
                onAddCmd = {
                    addCmd(packageProcessConfig)
                },
                onRemoveCmd = { cmd ->
                    removeCmd(packageProcessConfig, cmd)
                },
                onRemoveConfig = {
                    removeConfig(packageProcessConfig)
                },
                onCopyConfig = {
                    copyConfig(packageProcessConfig)
                },
                onCopyCmd = { cmd ->
                    onCopyCmd(packageProcessConfig, cmd)
                }
            )
        }
        Column {
            Button({
                addConfig()
            }) {
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    tint = Color.White,
                    contentDescription = "",
                )
                Text("添加配置")
            }
        }

        Row {

            Row {
                Button({
                    PluginSettingsStateComponent.instance.loadState(initialSetting.toStateComponent())
                    ApplicationManager.getApplication().invokeAndWait {
                        ApplicationManager.getApplication().saveSettings()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Done,
                        tint = Color.White,
                        contentDescription = "",
                    )
                    Text("保存")
                }
            }

            Row {
                Button({ exportPluginConfig(project) }) {
                    Icon(
                        imageVector = Icons.Default.ThumbUp,
                        tint = Color.White,
                        contentDescription = "",
                    )
                    Text("导出配置")
                }
            }
            Row {
                Button({
                    chooseFileToImport(project)
                    ApplicationManager.getApplication().invokeAndWait {
                        ApplicationManager.getApplication().saveSettings()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Build,
                        tint = Color.White,
                        contentDescription = "",
                    )
                    Text("导入配置")
                }
            }
        }
    }


}

private fun exportPluginConfig(project: Project) {
    val currentState = PluginSettingsStateComponent.instance.state
    val element = XmlSerializer.serialize(currentState)
    val xmlTextStr = JDOMUtil.writeElement(element)
    FileTool.exportConfig(project, xmlTextStr)
}
