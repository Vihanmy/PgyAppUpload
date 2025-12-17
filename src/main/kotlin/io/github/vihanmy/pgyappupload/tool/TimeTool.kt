package io.github.vihanmy.pgyappupload.tool

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object TimeTool {
    /**
     * "yyyy-MM-dd HH:mm:ss"
     */
    fun getShowTimeNow(format: String = "yyyy-MM-dd"): String {
        val now = LocalDateTime.now()
        val text = now.format(DateTimeFormatter.ofPattern(format))
        return text
    }
}