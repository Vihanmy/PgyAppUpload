package io.github.vihanmy.pgyappupload.jcomponent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposePanel
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import io.github.vihanmy.pgyappupload.model.PluginSettingsStateComponent
import io.github.vihanmy.pgyappupload.model.uistate.PluginSettingsUiState
import io.github.vihanmy.pgyappupload.pages.PagePluginSetting
import java.awt.Dimension
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
import java.awt.event.ComponentListener
import java.awt.event.HierarchyEvent

class PluginSettingsComponent(
    originalSettings: PluginSettingsStateComponent,
) {
    var settingsState = PluginSettingsUiState()

    init {
        settingsState.updateFromStateComponent(originalSettings)
    }

    private var height by mutableStateOf(10)
    private var width by mutableStateOf(10)

    val panel by lazy {
        ComposePanel().apply {

            this.addHierarchyListener { e ->
                if ((e.changeFlags and HierarchyEvent.PARENT_CHANGED.toLong()) != 0L) {
                    val parent = this.parent
                    parent?.addComponentListener(object : ComponentAdapter() {
                        override fun componentResized(e: ComponentEvent?) {
                            println("parent size:${e?.component?.width}  ${e?.component?.height}")
                            this@PluginSettingsComponent.height = parent.height
                            this@PluginSettingsComponent.width = parent.width
                            this@apply.size = Dimension(parent.width, parent.height)
                            this@apply.revalidate()
                            this@apply.repaint()
                        }
                    })
                }
            }

            addComponentListener(object : ComponentListener {
                override fun componentResized(e: ComponentEvent?) {
                    println("root componentResized:${e?.component?.width}  ${e?.component?.height}")
                }

                override fun componentMoved(e: ComponentEvent?) {}
                override fun componentShown(e: ComponentEvent?) {}
                override fun componentHidden(e: ComponentEvent?) {}
            })

            setContent {
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
                    val density = LocalDensity.current
                    Box(
                        modifier = Modifier
                            .height(with(density) { this@PluginSettingsComponent.height.toFloat().toDp() })
                            .width(with(density) { this@PluginSettingsComponent.width.toFloat().toDp() })
                    ) {
                        PagePluginSetting(settingsState)
                    }
                }
            }
        }
    }
}
