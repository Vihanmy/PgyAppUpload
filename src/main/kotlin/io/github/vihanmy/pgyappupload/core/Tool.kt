package io.github.vihanmy.pgyappupload.core

import com.google.gson.GsonBuilder
import com.intellij.execution.configurations.CommandLineTokenizer
import com.intellij.openapi.fileChooser.FileChooser
import com.intellij.openapi.fileChooser.FileChooserDescriptor
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.vfs.isFile
import io.github.vihanmy.pgyappupload.model.consts.PluginConfig
import io.github.vihanmy.pgyappupload.model.network.ResultCheckBean
import java.io.File
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

    fun parseEvn(evenSStr: String): Map<String, String> {
        val result = mutableMapOf<String, String>()
        val list = evenSStr.split("\n")
        list.forEachIndexed { index, key ->
            if ((index % 2) == 0) {
                val value = list.getOrNull(index + 1)
                if (value != null) {
                    result[key] = value
                }
            }
        }
        return result
    }

    fun chooseFileAndUpload(project: Project): VirtualFile? {
        val descriptor = FileChooserDescriptor(true, true, true, true, true, false)
            .withTitle("选择要上传的安装包")
            .withDescription("支持安卓(.apk)和鸿蒙(.hap)")
            .withShowHiddenFiles(true)
            .withFileFilter { (it.extension in PluginConfig.supportAppFileExt) and (it.isFile) }
        //
        val chosenFiles = FileChooser.chooseFiles(descriptor, project, null)
        return chosenFiles.getOrNull(0)
    }

    fun getFileLastModifiedTime(vf: VirtualFile): String? {
        return try {
            val path = vf.toNioPath()
            val attrs: BasicFileAttributes = Files.readAttributes(path, BasicFileAttributes::class.java)
            val creationInstant: Instant = attrs.lastModifiedTime().toInstant()

            // 格式化为本地可读时间（例如：2025-11-10 14:32:10）
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.systemDefault())

            formatter.format(creationInstant)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun getResultStr(resultCheckBean: ResultCheckBean?, isSuccess: Boolean, filePath: String): String {
        val log = if (resultCheckBean != null) {

            val gson = GsonBuilder()
                .setPrettyPrinting()
                .create()

            "--------------------------------------------------------------------" + "\n" + gson.toJson(resultCheckBean) + "\n" + "--------------------------------------------------------------------"
        } else {
            ""
        }

        val successStr = """
上传成功! ( $filePath )
$log
""".trimIndent()
        return if (isSuccess) successStr else "上传失败请重试!"
    }

    fun checkOutPutFile(filePath: String, processStartTime: Long?): Pair<Boolean, String?> {
        val file = File(filePath)
        if (file.exists().not()) {
            return Pair(false, "产物文件不存在")
        }
        if (file.isDirectory) {
            return Pair(false, "这是一个文件夹, 不是文件")
        }
        if (file.extension !in PluginConfig.supportAppFileExt) {
            return Pair(false, "不支持的文件类型")
        }
        val fileTime = Files.getLastModifiedTime(file.toPath())

        if (processStartTime != null) {
            val lastModifiedTime = fileTime.toMillis()
            if (lastModifiedTime < processStartTime) {
                return Pair(false, "文件在执行命令前就已经存在")
            }
        }

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            .withZone(ZoneId.systemDefault())
        val modifiedTimeStr = formatter.format(fileTime.toInstant())

       val info =  """
🎉产物合法!
产物路径:${filePath}
更新时间:${modifiedTimeStr}
""".trimIndent()
        return Pair(true, info)
    }
}