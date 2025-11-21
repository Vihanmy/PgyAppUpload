package io.github.vihanmy.pgyappupload.tool

import com.intellij.execution.ExecutorRegistry
import com.intellij.execution.impl.ConsoleViewImpl
import com.intellij.execution.ui.RunContentDescriptor
import com.intellij.execution.ui.RunContentManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindowId
import com.intellij.openapi.wm.ToolWindowManager

object ConsoleViewTool {
    fun createConsole(project: Project): ConsoleViewImpl {
        //██ ConsoleView  in  ToolWindow
        // 3️⃣ 创建 ConsoleView（IDE 自带控制台）
        val consoleView = ConsoleViewImpl(project, false)

        // 4️⃣ 创建 RunContentDescriptor（运行内容描述）
        val descriptor = RunContentDescriptor(null, null, consoleView.component, "命令执行")

        // 5️⃣ 将控制台内容显示在 Build / Run 窗口
        val toolWindow = ToolWindowManager.getInstance(project).getToolWindow(ToolWindowId.RUN)
        val manager = RunContentManager.getInstance(project)
        // 将 consoleView 显示在 toolWindow 中
        manager.showRunContent(ExecutorRegistry.getInstance().getExecutorById(ToolWindowId.RUN)!!, descriptor)
        toolWindow?.activate(null)

        return consoleView
    }
}