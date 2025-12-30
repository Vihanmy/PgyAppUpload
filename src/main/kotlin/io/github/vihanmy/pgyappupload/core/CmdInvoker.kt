package io.github.vihanmy.pgyappupload.core

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.impl.ConsoleViewImpl
import com.intellij.execution.process.ColoredProcessHandler
import com.intellij.execution.process.ProcessAdapter
import com.intellij.execution.process.ProcessEvent
import com.intellij.execution.ui.ConsoleViewContentType
import com.intellij.openapi.project.Project
import io.github.vihanmy.pgyappupload.model.CmdConfig
import java.nio.charset.Charset
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class CmdInvoker(
    private val cmdConfig: CmdConfig,
    private val consoleView: ConsoleViewImpl,
    private val project: Project,
) {

    suspend fun run(): Result<Unit> = suspendCoroutine { continuation ->
        val command: List<String> = Tool.parseCommandLine(cmdConfig.cmd)
        val workingDir: String = if (cmdConfig.workDir.isNullOrBlank()) project.basePath!! else cmdConfig.workDir
        val env: Map<String, String> = Tool.parseEvn(cmdConfig.evenStr)

        consoleView.print("---------------${cmdConfig.name}---------------------------\n", ConsoleViewContentType.LOG_INFO_OUTPUT)
        consoleView.print("开始执行 :${cmdConfig.cmd}\n", ConsoleViewContentType.LOG_INFO_OUTPUT)
        consoleView.print("工作目录 :${cmdConfig.workDir}\n", ConsoleViewContentType.LOG_INFO_OUTPUT)
        consoleView.print("环境变量 :${cmdConfig.evenStr}\n", ConsoleViewContentType.LOG_INFO_OUTPUT)
        env.forEach { (key, value) ->
            consoleView.print("$key -> $value\n", ConsoleViewContentType.LOG_INFO_OUTPUT)
        }

        try {
            val generalCmd = GeneralCommandLine(command)
                .withRedirectErrorStream(true)
                .withCharset(Charset.forName("UTF-8"))
                .withWorkDirectory(workingDir)
                .apply {
                    env.forEach { (key, value) ->
                        withEnvironment(key, value)
                    }
                }

            val processHandler = ColoredProcessHandler(generalCmd)
            consoleView.attachToProcess(processHandler)
            //
            processHandler.addProcessListener(object : ProcessAdapter() {
                override fun processTerminated(event: ProcessEvent) {
                    if (event.exitCode == 0) {
                        continuation.resume(Result.success(Unit))
                    } else {
                        continuation.resume(Result.failure(Error("Process terminated with exit code ${event.exitCode}")))
                    }
                }
            })
            processHandler.startNotify()
        } catch (e: Exception) {
            e.printStackTrace()
            continuation.resume(Result.failure(e))
        }
    }
}