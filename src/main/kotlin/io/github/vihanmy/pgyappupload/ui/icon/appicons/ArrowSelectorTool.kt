package io.github.vihanmy.pgyappupload.ui.icon.appicons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import io.github.vihanmy.pgyappupload.ui.icon.AppIcons

public val AppIcons.ArrowSelectorTool: ImageVector
    get() {
        if (_arrowSelectorTool != null) {
            return _arrowSelectorTool!!
        }
        _arrowSelectorTool = Builder(
            name = "ArrowSelectorTool", defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp, viewportWidth = 960.0f, viewportHeight = 960.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFe3e3e3)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(320.0f, 550.0f)
                lineToRelative(79.0f, -110.0f)
                horizontalLineToRelative(170.0f)
                lineTo(320.0f, 244.0f)
                verticalLineToRelative(306.0f)
                close()
                moveTo(606.0f, 855.0f)
                quadToRelative(-23.0f, 11.0f, -46.0f, 2.5f)
                reflectiveQuadTo(526.0f, 826.0f)
                lineTo(406.0f, 568.0f)
                lineToRelative(-93.0f, 130.0f)
                quadToRelative(-17.0f, 24.0f, -45.0f, 15.0f)
                reflectiveQuadToRelative(-28.0f, -38.0f)
                verticalLineToRelative(-513.0f)
                quadToRelative(0.0f, -25.0f, 22.5f, -36.0f)
                reflectiveQuadToRelative(42.5f, 5.0f)
                lineToRelative(404.0f, 318.0f)
                quadToRelative(23.0f, 17.0f, 13.5f, 44.0f)
                reflectiveQuadTo(684.0f, 520.0f)
                lineTo(516.0f, 520.0f)
                lineToRelative(119.0f, 255.0f)
                quadToRelative(11.0f, 23.0f, 2.5f, 46.0f)
                reflectiveQuadTo(606.0f, 855.0f)
                close()
                moveTo(399.0f, 440.0f)
                close()
            }
        }
            .build()
        return _arrowSelectorTool!!
    }

private var _arrowSelectorTool: ImageVector? = null

