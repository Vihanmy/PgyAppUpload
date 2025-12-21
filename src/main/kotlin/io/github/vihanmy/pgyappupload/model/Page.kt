package io.github.vihanmy.pgyappupload.model

sealed class Page(
    val titleStr: String
) {

    open fun getTitle(): String {
        return titleStr
    }

    /////////////////////////////////////////////////////////
    data object Home : Page("主页")
    data object ChooseFile4Upload : Page("选择文件上传")

    data object Setting : Page("设置")
}

