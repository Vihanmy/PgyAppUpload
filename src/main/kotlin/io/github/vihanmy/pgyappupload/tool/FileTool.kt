package io.github.vihanmy.pgyappupload.tool

import com.intellij.openapi.fileChooser.FileChooserFactory
import com.intellij.openapi.fileChooser.FileSaverDescriptor
import com.intellij.openapi.project.Project

object FileTool {

    /**
     * 导出配置文件
     */
    fun exportConfig(project: Project, content: String) {
        val descriptor = FileSaverDescriptor("导出插件配置", "请选择配置文件保存位置", "xml")
        val dialog = FileChooserFactory.getInstance().createSaveFileDialog(descriptor, project)
        val nameWithOutExt = "PgyAppUploadConfig_${TimeTool.getShowTimeNow()}.xml"
        //
        val result = dialog.save(nameWithOutExt) ?: return
        //
        val targetFile = result.file
        //
        targetFile.parentFile?.mkdirs()
        targetFile.writeText(content)
    }

}