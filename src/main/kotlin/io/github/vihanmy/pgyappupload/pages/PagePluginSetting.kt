package io.github.vihanmy.pgyappupload.pages

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.vihanmy.pgyappupload.model.uistate.CmdConfigUiState
import io.github.vihanmy.pgyappupload.model.uistate.PackageProcessConfigUiState
import io.github.vihanmy.pgyappupload.model.uistate.PluginSettingsUiState
import io.github.vihanmy.pgyappupload.ui.UIPackageProcessConfig

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

    Column(Modifier.verticalScroll(rememberScrollState())) {
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
    }
}