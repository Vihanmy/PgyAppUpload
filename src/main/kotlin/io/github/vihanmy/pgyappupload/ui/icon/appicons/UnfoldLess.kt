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

public val AppIcons.UnfoldLess: ImageVector
    get() {
        if (_unfoldLess != null) {
            return _unfoldLess!!
        }
        _unfoldLess = Builder(
            name = "UnfoldLess", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
            viewportWidth = 960.0f, viewportHeight = 960.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFe3e3e3)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(356.0f, 800.0f)
                lineToRelative(-56.0f, -56.0f)
                lineToRelative(180.0f, -180.0f)
                lineToRelative(180.0f, 180.0f)
                lineToRelative(-56.0f, 56.0f)
                lineToRelative(-124.0f, -124.0f)
                lineToRelative(-124.0f, 124.0f)
                close()
                moveTo(480.0f, 396.0f)
                lineTo(300.0f, 216.0f)
                lineToRelative(56.0f, -56.0f)
                lineToRelative(124.0f, 124.0f)
                lineToRelative(124.0f, -124.0f)
                lineToRelative(56.0f, 56.0f)
                lineToRelative(-180.0f, 180.0f)
                close()
            }
        }
            .build()
        return _unfoldLess!!
    }

private var _unfoldLess: ImageVector? = null
