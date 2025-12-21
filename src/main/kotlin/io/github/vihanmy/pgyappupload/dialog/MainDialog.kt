package io.github.vihanmy.pgyappupload.dialog

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposePanel
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import io.github.vihanmy.pgyappupload.core.PageRouter
import io.github.vihanmy.pgyappupload.model.Page
import io.github.vihanmy.pgyappupload.model.PluginSettingsStateComponent
import io.github.vihanmy.pgyappupload.model.uistate.PluginSettingsUiState
import io.github.vihanmy.pgyappupload.pages.PageChooseFileToUpload
import io.github.vihanmy.pgyappupload.pages.PageHome
import io.github.vihanmy.pgyappupload.pages.PagePluginSetting
import io.github.vihanmy.pgyappupload.ui.icon.AppIcons
import io.github.vihanmy.pgyappupload.ui.icon.appicons.Files
import io.github.vihanmy.pgyappupload.ui.icon.appicons.Home
import io.github.vihanmy.pgyappupload.ui.icon.appicons.Settings
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

    private var page = mutableStateOf<Page>(Page.Home)


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
                val lightColors = MaterialTheme.colors.copy(
                    surface = Color(0xfff6f6f6),
                )
                val typography = MaterialTheme.typography.copy(
                    h1 = MaterialTheme.typography.h1.copy(
                        fontSize = 18.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = lightColors.onSurface
                    ),
                    h2 = MaterialTheme.typography.h2.copy(
                        fontSize = 16.sp,
                        lineHeight = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = lightColors.onSurface
                    ),
                    h3 = MaterialTheme.typography.h3.copy(
                        fontSize = 14.sp,
                        lineHeight = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = lightColors.onSurface
                    ),
                    body1 = MaterialTheme.typography.body1.copy(fontSize = 12.sp)
                )
                MaterialTheme(
                    colors = lightColors,
                    typography = typography,
                ) {
                    CompositionLocalProvider(
                        RouterProvidableCompositionLocal provides router,
                        ProJectProvidableCompositionLocal provides project,
                    ) {
                        Row(
                            modifier = Modifier
                                .background(MaterialTheme.colors.background)
                                .width(PxToDp(containerWidth.toFloat()).dp)
                                .height(PxToDp(containerHeight.toFloat()).dp)
                        ) {
                            //【】tabs
                            Column(
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .wrapContentWidth()
                            ) {

                                UIMenuOption(
                                    icon = AppIcons.Home,
                                    title = "主页",
                                    currentPage = page,
                                    targetPage = Page.Home
                                )

                                UIMenuOption(
                                    icon = AppIcons.Files,
                                    title = "选择文件",
                                    currentPage = page,
                                    targetPage = Page.ChooseFile4Upload
                                )

                                UIMenuOption(
                                    icon = AppIcons.Settings,
                                    title = "设置",
                                    currentPage = page,
                                    targetPage = Page.Setting
                                )

                            }
                            //【】pages
                            Box(
                                Modifier.weight(1f)
                                    .fillMaxHeight()
                                    .padding(12.dp)
                            ) {

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight()
                                        //
                                        .shadow(5.dp, RoundedCornerShape(8.dp))
                                        //
                                        .background(MaterialTheme.colors.surface)
                                        //
                                        .padding(12.dp)
                                        .verticalScroll(rememberScrollState()),
                                ) {
                                    when (page.value) {
                                        Page.Home -> PageHome()
                                        is Page.ChooseFile4Upload -> PageChooseFileToUpload()
                                        is Page.Setting -> PagePluginSetting(PluginSettingsUiState().apply {
                                            updateFromStateComponent(PluginSettingsStateComponent.instance)
                                        })
                                    }
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

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UIMenuOption(
    icon: ImageVector,
    title: String,
    currentPage: MutableState<Page>,
    targetPage: Page,
) {
    val isSelected by remember { derivedStateOf { currentPage.value == targetPage } }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.wrapContentWidth()
            .padding(start = 12.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.background)
            .padding(end = 10.dp)
            .onClick {
                currentPage.value = targetPage
            }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "",
            modifier = Modifier.padding(10.dp),
            tint = if (isSelected) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onBackground.copy(alpha = 0.7f)
        )
        Text(
            title,
            color = if (isSelected) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onBackground.copy(alpha = 0.7f),
            modifier = Modifier.width(100.dp)
        )
    }
}