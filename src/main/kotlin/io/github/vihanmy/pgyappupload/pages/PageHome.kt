package io.github.vihanmy.pgyappupload.pages

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import io.github.vihanmy.pgyappupload.dialog.RouterProvidableCompositionLocal
import io.github.vihanmy.pgyappupload.model.Page

/**
 *# 主页
 * ```
 * author :Vihanmy
 * date   :2025/11/09 10:10
 * desc   :
 * ```
 */
@Preview
@Composable
fun PageHome() {
    val router = RouterProvidableCompositionLocal.current

    //
    Column {
        Button({
            router.push(Page.ChooseFile4Upload())
        }) {
            Text("选择已有文件上传")
        }
    }
}