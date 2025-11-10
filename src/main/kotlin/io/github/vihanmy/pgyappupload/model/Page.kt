package io.github.vihanmy.pgyappupload.model

sealed class Page(
    val titleStr: String
) {

    open fun getTitle(): String {
        return titleStr
    }

    /////////////////////////////////////////////////////////
    object Home : Page("主页")
    class ChooseFile4Upload : Page("选择文件上传")
}

