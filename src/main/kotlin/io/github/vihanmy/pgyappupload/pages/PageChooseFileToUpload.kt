package io.github.vihanmy.pgyappupload.pages

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import io.github.vihanmy.pgyappupload.core.AppUploadTool
import io.github.vihanmy.pgyappupload.core.AppUploadTool.Companion.CHECK_TIME_ONCE
import io.github.vihanmy.pgyappupload.core.AppUploadTool.Companion.MAX_CHECK_TIME
import io.github.vihanmy.pgyappupload.core.Tool
import io.github.vihanmy.pgyappupload.core.Tool.getFileLastModifiedTime
import io.github.vihanmy.pgyappupload.dialog.ProJectProvidableCompositionLocal
import io.github.vihanmy.pgyappupload.model.PluginSettingsStateComponent
import io.github.vihanmy.pgyappupload.model.network.ResultCheckBean
import io.github.vihanmy.pgyappupload.model.processstep.ProcessStep
import io.github.vihanmy.pgyappupload.model.processstep.ProcessStepState
import io.github.vihanmy.pgyappupload.model.processstep.ProcessStepWithProgress
import io.github.vihanmy.pgyappupload.ui.UIProgressStep
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
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
    var fileLastModifiedTime by remember { mutableStateOf("") }
    val progressStep = remember { mutableStateListOf<ProcessStep>() }

    fun chooseFileToUpload() {
        val targetFile = Tool.chooseFileAndUpload(project) ?: return
        filePath = targetFile.path
        fileLastModifiedTime = getFileLastModifiedTime(targetFile) ?: ""
    }


    fun startUpload() {
        progressStep.clear()
        //██ 获取配置的 api key
        val stepOfApiKeyVerify = ProcessStep("获取配置的 API KEY")
        progressStep.add(stepOfApiKeyVerify)
        val apiKey: String? = PluginSettingsStateComponent.instance.pgyApiKey
        stepOfApiKeyVerify.state = if (apiKey.isNullOrBlank()) ProcessStepState.Failed else ProcessStepState.Success
        val hasApiKey = apiKey.isNullOrBlank().not()
        stepOfApiKeyVerify.markState(hasApiKey, if (hasApiKey) "使用的 KEY 是 : $apiKey" else "未配置API KEY")
        if (hasApiKey.not()) return

        stepOfApiKeyVerify.showStepLine = true

        CoroutineScope(Dispatchers.IO).launch {
            //██ 获取文件上传Token
            val stepOfGetToken = ProcessStep("获取文件上传Token")
            progressStep.add(stepOfGetToken)
            val uploadTool = AppUploadTool(apiKey, filePath)
            val tokenBean = uploadTool.fetchToken()
            val buildKey = tokenBean?.params?.key
            //
            val errorMsg = if (buildKey == null) {
                "Token 获取失败"
            } else {
                "Token 获取成功"
            }
            stepOfGetToken.markState(buildKey != null, errorMsg)
            if (buildKey == null) return@launch
            stepOfGetToken.showStepLine = true

            //██ 文件上传
            val stepOfFileUpload = ProcessStepWithProgress("文件上传", "文件上传中")
            progressStep.add(stepOfFileUpload)
            val uploadResult = uploadTool.updateFile(tokenBean, filePath) { progress ->
                stepOfFileUpload.progress = progress
            }
            stepOfFileUpload.markState(uploadResult, if (uploadResult) "文件上传成功" else "文件上传失败")
            stepOfFileUpload.showStepLine = true

            if (uploadResult.not()) return@launch

            //██ 结果检测
            val resultCheckStep = ProcessStep("处理结果检测", "检测中")
            progressStep.add(resultCheckStep)
            var testTime = 1
            var isSuccess = false
            var resultCheckBean: ResultCheckBean? = null
            while (testTime <= MAX_CHECK_TIME) {
                resultCheckStep.msg = "结果检测中(${testTime})"
                delay(CHECK_TIME_ONCE)
                resultCheckBean = uploadTool.resultCheck(apiKey, buildKey)
                if (resultCheckBean != null) {
                    isSuccess = true
                    break
                }
                testTime++
            }

            val re = Tool.getResultStr(resultCheckBean, isSuccess, filePath)
            resultCheckStep.markState(isSuccess, re)
        }

    }
    //
    Column(
        modifier = Modifier.wrapContentHeight(),
    ) {
        Button({
            chooseFileToUpload()
        }) {
            Text(if (filePath.isNotBlank()) "重新选择文件" else "选择文件")
        }

        if (filePath.isNotBlank()) {
            Text("选择的文件是 : ")
            Text(text = filePath, fontWeight = FontWeight.Bold)
            Text("文件更新时间 : ")
            Text(text = fileLastModifiedTime, fontWeight = FontWeight.Bold)
            Button({
                startUpload()
            }) {
                Text("开始上传")
            }
        }

        progressStep.forEach { step ->
            UIProgressStep(step)
        }
    }
}