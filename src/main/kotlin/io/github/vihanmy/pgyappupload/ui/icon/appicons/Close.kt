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

public val AppIcons.Close: ImageVector
    get() {
        if (_close != null) {
            return _close!!
        }
        _close = Builder(
            name = "Close", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
            viewportWidth = 960.0f, viewportHeight = 960.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFe3e3e3)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(480.0f, 536.0f)
                lineTo(284.0f, 732.0f)
                quadToRelative(-11.0f, 11.0f, -28.0f, 11.0f)
                reflectiveQuadToRelative(-28.0f, -11.0f)
                quadToRelative(-11.0f, -11.0f, -11.0f, -28.0f)
                reflectiveQuadToRelative(11.0f, -28.0f)
                lineToRelative(196.0f, -196.0f)
                lineToRelative(-196.0f, -196.0f)
                quadToRelative(-11.0f, -11.0f, -11.0f, -28.0f)
                reflectiveQuadToRelative(11.0f, -28.0f)
                quadToRelative(11.0f, -11.0f, 28.0f, -11.0f)
                reflectiveQuadToRelative(28.0f, 11.0f)
                lineToRelative(196.0f, 196.0f)
                lineToRelative(196.0f, -196.0f)
                quadToRelative(11.0f, -11.0f, 28.0f, -11.0f)
                reflectiveQuadToRelative(28.0f, 11.0f)
                quadToRelative(11.0f, 11.0f, 11.0f, 28.0f)
                reflectiveQuadToRelative(-11.0f, 28.0f)
                lineTo(536.0f, 480.0f)
                lineToRelative(196.0f, 196.0f)
                quadToRelative(11.0f, 11.0f, 11.0f, 28.0f)
                reflectiveQuadToRelative(-11.0f, 28.0f)
                quadToRelative(-11.0f, 11.0f, -28.0f, 11.0f)
                reflectiveQuadToRelative(-28.0f, -11.0f)
                lineTo(480.0f, 536.0f)
                close()
            }
        }
            .build()
        return _close!!
    }

private var _close: ImageVector? = null

