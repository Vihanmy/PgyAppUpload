package io.github.vihanmy.pgyappupload.dialog

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.onClick
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposePanel
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import io.github.vihanmy.pgyappupload.core.PageRouter
import io.github.vihanmy.pgyappupload.model.Page
import io.github.vihanmy.pgyappupload.model.PluginSettingsStateComponent
import io.github.vihanmy.pgyappupload.model.uistate.PluginSettingsUiState
import io.github.vihanmy.pgyappupload.pages.PageChooseFileToUpload
import io.github.vihanmy.pgyappupload.pages.PageHome
import io.github.vihanmy.pgyappupload.pages.PagePluginSetting
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
import javax.swing.Action
import javax.swing.JComponent

val RouterProvidableCompositionLocal = staticCompositionLocalOf<PageRouter> {
    error("No AppConfig provided")
}
val ProJectProvidableCompositionLocal = staticCompositionLocalOf<Project> {
    error("No AppConfig provided")
}

class MainDialog(
    private val project: Project,
) : DialogWrapper(project, true, IdeModalityType.MODELESS) {

    private var containerWidth by mutableStateOf(800)
    private var containerHeight by mutableStateOf(600)
    private val router = PageRouter()

    init {
        init()
    }

    override fun createActions(): Array<out Action?> {
        return emptyArray()
    }

    @OptIn(ExperimentalFoundationApi::class)
    override fun createCenterPanel(): JComponent {
        return ComposePanel().apply {
            setBounds(0, 0, 800, 600)
            addComponentListener(object : ComponentAdapter() {
                override fun componentResized(e: ComponentEvent) {
                    containerWidth = width
                    containerHeight = height
                }
            })
            setContent {
                CompositionLocalProvider(
                    RouterProvidableCompositionLocal provides router,
                    ProJectProvidableCompositionLocal provides project,
                ) {
                    Column(
                        modifier = Modifier
                            .width(PxToDp(containerWidth.toFloat()).dp)
                            .height(PxToDp(containerHeight.toFloat()).dp),
                    ) {
                        //【】标题
                        TitleArea()
                        //【】页面
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                                .weight(1f)
                                .verticalScroll(rememberScrollState()),
                        ) {
                            when (router.pageStack.lastOrNull()) {
                                Page.Home -> PageHome()
                                is Page.ChooseFile4Upload -> PageChooseFileToUpload()
                                is Page.Setting -> PagePluginSetting(PluginSettingsUiState().apply {
                                    updateFromStateComponent(PluginSettingsStateComponent.instance)
                                })
                                //
                                null -> {
                                    Text("Noting to display")
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    @Composable
    fun PxToDp(px: Float): Float {
        val density = LocalDensity.current
        return with(density) { px.toDp().value }
    }

    @Composable
    @OptIn(ExperimentalFoundationApi::class)
    private fun TitleArea() {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = if (router.pageStack.size == 1) Icons.Filled.Home else Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "返回",
                modifier = Modifier.padding(10.dp).onClick {
                    router.pop()
                },
            )
            Text(router.pageStack.lastOrNull()?.getTitle() ?: "", maxLines = 1, modifier = Modifier.weight(1f))
            if (router.pageStack.lastOrNull() != Page.Setting) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "菜单",
                    modifier = Modifier.padding(10.dp).onClick {
                        router.push(Page.Setting)
                    },
                )
            }
        }
    }

}