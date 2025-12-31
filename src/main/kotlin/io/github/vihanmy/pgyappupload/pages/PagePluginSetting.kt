package io.github.vihanmy.pgyappupload.pages

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import io.github.vihanmy.pgyappupload.ui.UIPureIconButton
import io.github.vihanmy.pgyappupload.ui.icon.AppIcons
import io.github.vihanmy.pgyappupload.ui.icon.appicons.Add
import io.github.vihanmy.pgyappupload.ui.icon.appicons.ExportNotes
import io.github.vihanmy.pgyappupload.ui.icon.appicons.MoveToInbox
import io.github.vihanmy.pgyappupload.ui.icon.appicons.Save
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

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier.wrapContentHeight()
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colors.background)
                    .padding(10.dp)
            ) {
                Text("蒲公英平台 API KEY", style = MaterialTheme.typography.h1)
                BasicTextField(
                    value = initialSetting.pgyApiKey,
                    textStyle = MaterialTheme.typography.body1,
                    onValueChange = {
                        initialSetting.pgyApiKey = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colors.surface.copy(0.8f))
                        .padding(10.dp),
                )
            }
            Column(
                Modifier.wrapContentHeight()
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colors.background)
                    .padding(10.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.wrapContentHeight().fillMaxWidth()
                ) {
                    Text("工作流配置", style = MaterialTheme.typography.h1)
                    Box(modifier = Modifier.weight(1f))
                    UIPureIconButton(AppIcons.Add) {
                        addConfig()
                    }
                }

                for (packageProcessConfig in initialSetting.packageProcessConfigList) {
                    Box(Modifier.height(20.dp))
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
            }
        }
        Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth().padding(top = 20.dp)) {
            Row {
                Button({
                    PluginSettingsStateComponent.instance.loadState(initialSetting.toStateComponent())
                    ApplicationManager.getApplication().invokeAndWait {
                        ApplicationManager.getApplication().saveSettings()
                    }
                }) {
                    Icon(
                        imageVector = AppIcons.Save,
                        tint = Color.White,
                        contentDescription = "",
                    )
                    Text("保存")
                }
            }
            Box(Modifier.width(10.dp))
            Row {
                Button({ exportPluginConfig(project) }) {
                    Icon(
                        imageVector = AppIcons.ExportNotes,
                        tint = Color.White,
                        contentDescription = "",
                    )
                    Text("导出")
                }
            }
            Box(Modifier.width(10.dp))
            Row {
                Button({
                    chooseFileToImport(project)
                    ApplicationManager.getApplication().invokeAndWait {
                        ApplicationManager.getApplication().saveSettings()
                    }
                }) {
                    Icon(
                        imageVector = AppIcons.MoveToInbox,
                        tint = Color.White,
                        contentDescription = "",
                    )
                    Text("导入")
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
