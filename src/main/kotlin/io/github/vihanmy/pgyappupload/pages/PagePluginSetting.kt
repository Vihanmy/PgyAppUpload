package io.github.vihanmy.pgyappupload.pages

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.vihanmy.pgyappupload.model.uistate.PluginSettingsUiState

@Preview
@Composable
fun PagePluginSettingPreView() {
    PagePluginSetting()
}

/**
 *# page : 设置页面
 * ```
 * author :Vihanmy
 * date   :2025-11-09 12:28
 * desc   :
 * ```
 */
@Composable
fun PagePluginSetting(
    initialSetting: PluginSettingsUiState = PluginSettingsUiState(),
) {
    Column {
        Text(
            "蒲公英平台的 API KEY",
            color = MaterialTheme.colors.onSecondary
        )

        BasicTextField(
            value = initialSetting.pgyApiKey,
            onValueChange = {
                initialSetting.pgyApiKey = it
            },
            modifier = Modifier
                .border(1.dp, Color.Black)
                .padding(10.dp),
        )
    }
}