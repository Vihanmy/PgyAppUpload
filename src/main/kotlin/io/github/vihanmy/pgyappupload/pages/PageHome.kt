package io.github.vihanmy.pgyappupload.pages

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.onClick
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.vihanmy.pgyappupload.core.AppUploadTool
import io.github.vihanmy.pgyappupload.core.AppUploadTool.Companion.CHECK_TIME_ONCE
import io.github.vihanmy.pgyappupload.core.AppUploadTool.Companion.MAX_CHECK_TIME
import io.github.vihanmy.pgyappupload.core.CmdInvoker
import io.github.vihanmy.pgyappupload.core.Tool
import io.github.vihanmy.pgyappupload.dialog.ProJectProvidableCompositionLocal
import io.github.vihanmy.pgyappupload.dialog.RouterProvidableCompositionLocal
import io.github.vihanmy.pgyappupload.model.PackageProcessConfig
import io.github.vihanmy.pgyappupload.model.PluginSettingsStateComponent
import io.github.vihanmy.pgyappupload.model.network.ResultCheckBean
import io.github.vihanmy.pgyappupload.model.processstep.ProcessStep
import io.github.vihanmy.pgyappupload.model.processstep.ProcessStepState
import io.github.vihanmy.pgyappupload.model.processstep.ProcessStepWithProgress
import io.github.vihanmy.pgyappupload.tool.ConsoleViewTool
import io.github.vihanmy.pgyappupload.ui.UIProgressStep
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.nio.file.Paths
import kotlin.io.path.pathString


/**
 *# 主页
 * ```
 * author :Vihanmy
 * date   :2025/11/09 10:10
 * desc   :
 * ```
 */
@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun PageHome() {
    val router = RouterProvidableCompositionLocal.current
    val project = ProJectProvidableCompositionLocal.current
    val setting = PluginSettingsStateComponent.instance
    val progressStep = remember { mutableStateListOf<ProcessStep>() }

    fun processConfig(process: PackageProcessConfig) {
        progressStep.clear()
        val consoleView = ConsoleViewTool.createConsole(project)

        CoroutineScope(Dispatchers.IO).launch {
            val processStartTime = System.currentTimeMillis()

            //██执行打包命令
            process.cmdList.forEachIndexed { index, cmd ->
                val invoker = CmdInvoker(cmd, consoleView, project)
                val stepOfCmd = ProcessStep("命令行执行(${index + 1}):${cmd.name}", cmd.getDescription())
                progressStep.add(stepOfCmd)
                val runResult = invoker.run()
                val isSuccess = runResult.isSuccess
                stepOfCmd.markState(isSuccess, if (isSuccess) "命令执行成功" else runResult.exceptionOrNull().toString())
                if (isSuccess.not()) {
                    return@launch
                } else {
                    stepOfCmd.showStepLine = true
                }
            }

            //██ 打包产物校验
            val outPutCheckProcess = ProcessStep("产物校验", "校验产物的有效性")
            progressStep.add(outPutCheckProcess)
            val configPackegePath = process.packageOutPutPath
            val path = Paths.get(configPackegePath)
            val filePath =
                if (path.isAbsolute) {
                    configPackegePath
                } else {
                    Paths.get(project.basePath)
                        .resolve(configPackegePath)
                        .normalize()
                        .pathString
                }

            val checkResult = Tool.checkOutPutFile(filePath, if (process.cmdList.isEmpty()) null else processStartTime)
            val isCheckSuccess = checkResult.first
            outPutCheckProcess.markState(isCheckSuccess, checkResult.second ?: "")
            if (isCheckSuccess.not()) return@launch
            outPutCheckProcess.showStepLine = true

            //██ 获取配置的 api key
            val stepOfApiKeyVerify = ProcessStep("获取配置的 API KEY")
            progressStep.add(stepOfApiKeyVerify)
            val apiKey: String = PluginSettingsStateComponent.instance.pgyApiKey
            stepOfApiKeyVerify.state = if (apiKey.isNullOrBlank()) ProcessStepState.Failed else ProcessStepState.Success
            val hasApiKey = apiKey.isNullOrBlank().not()
            stepOfApiKeyVerify.markState(hasApiKey, if (hasApiKey) "使用的 KEY 是 : $apiKey" else "未配置API KEY")
            if (hasApiKey.not()) return@launch
            stepOfApiKeyVerify.showStepLine = true

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
    Column(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
        if (setting.packageProcessConfigList.isNotEmpty()) {
            setting.packageProcessConfigList.forEach { process ->
                Button({ processConfig(process) }) {
                    Text("\uD83C\uDFAC\uFE0F ${process.name}")
                }
            }
            progressStep.forEach { step ->
                UIProgressStep(step)
            }
        } else {
            Text(
                "暂无工作流程配置, 可前往设置进行配置⚙️",
                modifier = Modifier.padding(10.dp)
                    .onClick {

                    },
                style = MaterialTheme.typography.h1
            )
        }
    }
}