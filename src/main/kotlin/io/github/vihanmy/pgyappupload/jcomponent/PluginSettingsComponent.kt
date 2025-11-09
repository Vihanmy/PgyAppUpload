package io.github.vihanmy.pgyappupload.jcomponent

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.ui.awt.ComposePanel
import androidx.compose.ui.graphics.Color
import io.github.vihanmy.pgyappupload.model.PluginSettingsStateComponent
import io.github.vihanmy.pgyappupload.model.uistate.PluginSettingsUiState
import io.github.vihanmy.pgyappupload.pages.PagePluginSetting

class PluginSettingsComponent(
    originalSettings: PluginSettingsStateComponent,
) {
    var settingsState = PluginSettingsUiState()

    init {
        settingsState.updateFromStateComponent(originalSettings)
    }

    val panel by lazy {
        ComposePanel().apply {
            setBounds(0, 0, 800, 600)
            setContent {
                // TODO: Vihanmy 2025-11-09
                val LightColors = lightColors(
                    primary = Color(0xFF6200EE),
                    onPrimary = Color.White,
                    secondary = Color(0xFF03DAC6),
                    onSecondary = Color.Black,
                    background = Color(0xFFF6F6F6),
                    surface = Color.White,
                )

                val DarkColors = lightColors(
                    primary = Color(0xFFBB86FC),
                    onPrimary = Color.Black,
                    secondary = Color(0xFF03DAC6),
                    onSecondary = Color(0xff2b2d30),
                    background = Color(0xFF121212),
                    surface = Color(0xFF1E1E1E),
                )


                MaterialTheme(
                    colors = LightColors
                ) {
                    PagePluginSetting(settingsState)
                }
            }
        }
    }
}
