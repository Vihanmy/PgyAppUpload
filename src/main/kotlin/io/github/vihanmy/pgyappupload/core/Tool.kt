package io.github.vihanmy.pgyappupload.core

import com.intellij.execution.configurations.CommandLineTokenizer
import com.intellij.openapi.fileChooser.FileChooser
import com.intellij.openapi.fileChooser.FileChooserDescriptor
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import io.github.vihanmy.pgyappupload.model.consts.PluginConfig
import java.nio.file.Files
import java.nio.file.attribute.BasicFileAttributes
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object Tool {
    fun parseCommandLine(command: String): List<String> {
        val tokenizer = CommandLineTokenizer(command)
        val args = mutableListOf<String>()
        while (tokenizer.hasMoreTokens()) {
            args.add(tokenizer.nextToken())
        }
        return args
    }

    fun chooseFileAndUpload(project: Project): VirtualFile? {
        val descriptor = FileChooserDescriptor(true, false, false, false, false, false)
            .withTitle("选择要上传的安装包")
            .withDescription("支持安卓(.apk)和鸿蒙(.hap)")
            .withFileFilter { it.extension in PluginConfig.supportAppFileExt }
        //
        val chosenFiles = FileChooser.chooseFiles(descriptor, project, null)
        return chosenFiles.getOrNull(0)
    }

    fun getFileCreationTime(vf: VirtualFile): String? {
        return try {
            val path = vf.toNioPath()
            val attrs: BasicFileAttributes = Files.readAttributes(path, BasicFileAttributes::class.java)
            val creationInstant: Instant = attrs.creationTime().toInstant()

            // 格式化为本地可读时间（例如：2025-11-10 14:32:10）
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.systemDefault())

            formatter.format(creationInstant)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}