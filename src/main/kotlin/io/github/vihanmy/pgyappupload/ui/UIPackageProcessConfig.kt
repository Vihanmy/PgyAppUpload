package io.github.vihanmy.pgyappupload.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import io.github.vihanmy.pgyappupload.model.uistate.CmdConfigUiState
import io.github.vihanmy.pgyappupload.model.uistate.PackageProcessConfigUiState
import io.github.vihanmy.pgyappupload.ui.icon.AppIcons
import io.github.vihanmy.pgyappupload.ui.icon.appicons.*

@Composable
@Preview
fun UIPackageProcessConfigPreview() {
    UIPackageProcessConfig(PackageProcessConfigUiState().apply {

    })
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UIPackageProcessConfig(
    config: PackageProcessConfigUiState,
    onAddCmd: () -> Unit = {},
    onRemoveCmd: (CmdConfigUiState) -> Unit = { _ -> },
    onRemoveConfig: () -> Unit = {},
    onCopyConfig: () -> Unit = {},
    onCopyCmd: (CmdConfigUiState) -> Unit = {},
) {
    Column(
        Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colors.surface)
            .padding(10.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier) {
            Text(
                if (config.isExpend) "基础配置" else "配置: ${config.name}",
                style = MaterialTheme.typography.h2
            )

            Row(Modifier.weight(1f)) {}

            UIPureIconButton(AppIcons.Delete) {
                onRemoveConfig()
            }
            Box(Modifier.width(6.dp))
            UIPureIconButton(AppIcons.ContentCopy) {
                onCopyConfig()
            }
            Box(Modifier.width(6.dp))
            UIPureIconButton(if (config.isExpend) AppIcons.UnfoldLess else AppIcons.UnfoldMore) {
                config.isExpend = !config.isExpend
            }
        }

        if (config.isExpend) {
            Column(Modifier.fillMaxWidth()) {
                Column(
                    Modifier.fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colors.background)
                        .padding(10.dp)
                ) {
                    UIInputArea("配置名称", config.name) {
                        config.name = it
                    }
                    Box(Modifier.height(6.dp))
                    UIInputArea("产物路径", config.packageOutPutPath, hintInner = "支持相对路径和绝对路径(以./或名称开头)") {
                        config.packageOutPutPath = it
                    }
                }

                Box(Modifier.height(10.dp))

                Row(Modifier.fillMaxWidth()) {
                    Text("命令配置", style = MaterialTheme.typography.h2, modifier = Modifier.padding(vertical = 10.dp))
                    Box(Modifier.weight(1f))
                    UIPureIconButton(AppIcons.Add) {
                        onAddCmd()
                    }
                }

                for (cmdConfig in config.cmdList) {
                    UICmdConfig(
                        cmdConfig, config.cmdList.indexOf(cmdConfig),
                        onRemoveCmd = {
                            onRemoveCmd(cmdConfig)
                        },
                        onCopyCmd = {
                            onCopyCmd(cmdConfig)
                        }
                    )
                    Box(Modifier.height(8.dp))
                }


            }
        }

    }
}

@Composable
fun UICmdConfig(
    cmdConfig: CmdConfigUiState,
    index: Int,
    onRemoveCmd: () -> Unit = { },
    onCopyCmd: () -> Unit = { }
) {
    Column(
        Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colors.background)
            .padding(10.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom = 5.dp)) {
            Text(
                if (cmdConfig.isExpend.not() and cmdConfig.name.isBlank()
                        .not()
                ) cmdConfig.name else "命令: ${index + 1}",
                style = MaterialTheme.typography.h3
            )
            Box(Modifier.height(1.dp).weight(1f))
            UIPureIconButton(AppIcons.Delete) {
                onRemoveCmd()
            }
            Box(Modifier.width(6.dp))
            UIPureIconButton(AppIcons.ContentCopy) {
                onCopyCmd()
            }
            Box(Modifier.width(6.dp))
            UIPureIconButton(if (cmdConfig.isExpend) AppIcons.UnfoldLess else AppIcons.UnfoldMore) {
                cmdConfig.isExpend = !cmdConfig.isExpend
            }
        }

        if (cmdConfig.isExpend) {
            Box(Modifier.height(6.dp))
            UIInputArea("名称", cmdConfig.name) {
                cmdConfig.name = it
            }
            Box(Modifier.height(6.dp))
            UIInputArea("命令行", cmdConfig.cmd) {
                cmdConfig.cmd = it
            }
            Box(Modifier.height(6.dp))
            UIInputArea("工作目录(默认项目根目录)", cmdConfig.workDir) {
                cmdConfig.workDir = it
            }

            Box(Modifier.height(6.dp))
            UIInputArea("环境变量", cmdConfig.evenStr, "key, value 成对换行交替出现") {
                cmdConfig.evenStr = it
            }
        }
    }
}
