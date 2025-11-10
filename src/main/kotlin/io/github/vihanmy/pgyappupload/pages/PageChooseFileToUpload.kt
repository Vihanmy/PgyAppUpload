package io.github.vihanmy.pgyappupload.pages

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import io.github.vihanmy.pgyappupload.core.AppUploadTool
import io.github.vihanmy.pgyappupload.core.Tool
import io.github.vihanmy.pgyappupload.core.Tool.getFileCreationTime
import io.github.vihanmy.pgyappupload.dialog.ProJectProvidableCompositionLocal
import io.github.vihanmy.pgyappupload.model.PluginSettingsStateComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 *# 选择现有文件上传
 * ```
 * author :Vihanmy
 * date   :2025/11/09 10:10
 * desc   :
 * ```
 */
@Preview
@Composable
fun PageChooseFileToUpload() {
    val project = ProJectProvidableCompositionLocal.current
    var filePath by remember { mutableStateOf("") }
    var fileCreateTime by remember { mutableStateOf("") }
    var uploadProgress by remember { mutableFloatStateOf(0f) }

    fun chooseFileToUpload() {
        val targetFile = Tool.chooseFileAndUpload(project) ?: return
        filePath = targetFile.path
        fileCreateTime = getFileCreationTime(targetFile) ?: ""
    }

    fun startUpload() {
        val apiKey = PluginSettingsStateComponent.instance.pgyApiKey
        val uploadTool = AppUploadTool(
            apiKey, filePath,
            { logStr ->
                println(logStr)
            },
            { progress ->
                uploadProgress = progress
            })

        CoroutineScope(Dispatchers.IO).launch {
            uploadTool.upload()
        }
    }
    //
    Column {
        Button({
            chooseFileToUpload()
        }) {
            Text("选择文件")
        }

        if (filePath.isNotBlank()) {
            Text("选择的文件是: ")
            Text(filePath + " ( 创建时间 : ${fileCreateTime} )")
            Button({
                startUpload()
            }) {
                Text("开始上传")
            }

            if (uploadProgress > 0) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    LinearProgressIndicator(
                        progress = uploadProgress / 100f,
                        modifier = Modifier.height(10.dp).clip(CircleShape)
                    )
                    Text(" (${uploadProgress.toInt()}%)")
                }
            }
        }
    }
}