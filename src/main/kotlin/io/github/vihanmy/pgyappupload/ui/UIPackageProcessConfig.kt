package io.github.vihanmy.pgyappupload.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.vihanmy.pgyappupload.model.uistate.CmdConfigUiState
import io.github.vihanmy.pgyappupload.model.uistate.PackageProcessConfigUiState

@Composable
@Preview
fun UIPackageProcessConfigPreview() {
    UIPackageProcessConfig(PackageProcessConfigUiState().apply {

    })
}

@Composable
fun UIPackageProcessConfig(
    config: PackageProcessConfigUiState,
    onAddCmd: () -> Unit = {},
    onRemoveCmd: (CmdConfigUiState) -> Unit = { _ -> },
    onRemoveConfig: () -> Unit = {},
    onCopyConfig: () -> Unit = {},
    onCopyCmd: (CmdConfigUiState) -> Unit = {},
) {
    Column(Modifier.padding(10.dp).border(1.dp, Color.Black, MaterialTheme.shapes.medium).padding(15.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 10.dp, bottom = 5.dp)) {
            Text(
                "配置: ${config.name}",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(end = 4.dp)
            )

            Row(Modifier.height(1.dp).weight(1f).background(Color.Black)) {

            }

            Button({
                onRemoveConfig()
            }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    tint = Color.White,
                    contentDescription = "",
                )
            }
            Button({
                onCopyConfig()
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    tint = Color.White,
                    contentDescription = "",
                )
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("配置名称:")
            BasicTextField(
                value = config.name,
                onValueChange = {
                    config.name = it
                },
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .padding(10.dp),
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("产物路径:")
            BasicTextField(
                value = config.packageOutPutPath,
                onValueChange = {
                    config.packageOutPutPath = it
                },
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .padding(10.dp),
            )
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
        }

        Button({
            onAddCmd()
        }) {
            Icon(
                imageVector = Icons.Default.AddCircle,
                tint = Color.White,
                contentDescription = "",
            )
            Text("添加命令")
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
    Column {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 10.dp, bottom = 5.dp)) {
            Text(
                "命令行 ${index + 1}",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(end = 4.dp)
            )

            Row(Modifier.height(1.dp).weight(1f).background(Color.Black)) {

            }

            Button({
                onRemoveCmd()
            }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    tint = Color.White,
                    contentDescription = "",
                )
            }

            Button({
                onCopyCmd()
            }) {
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    tint = Color.White,
                    contentDescription = "",
                )
            }
        }
        Column {
            Text("名称:")
            BasicTextField(
                value = cmdConfig.name,
                onValueChange = {
                    cmdConfig.name = it
                },
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .padding(10.dp),
            )
        }
        Column {
            Text("命令行:")
            BasicTextField(
                value = cmdConfig.cmd,
                onValueChange = {
                    cmdConfig.cmd = it
                },
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .padding(10.dp),
            )
        }
        Column {
            Text("工作目录:(默认项目根目录)")
            BasicTextField(
                value = cmdConfig.workDir,
                onValueChange = {
                    cmdConfig.workDir = it
                },
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .padding(10.dp),
            )
        }
        Column {
            Text("环境变量")
            BasicTextField(
                value = cmdConfig.evenStr,
                onValueChange = {
                    cmdConfig.evenStr = it
                },
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .padding(10.dp),
            )
        }
    }
}
