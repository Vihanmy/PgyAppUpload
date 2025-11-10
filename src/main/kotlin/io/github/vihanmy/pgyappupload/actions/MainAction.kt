package io.github.vihanmy.pgyappupload.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import io.github.vihanmy.pgyappupload.dialog.MainDialog

class MainAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        MainDialog(e.project!!).show()
    }
}