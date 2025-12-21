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

public val AppIcons.Check: ImageVector
    get() {
        if (_check != null) {
            return _check!!
        }
        _check = Builder(
            name = "Check", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
            viewportWidth = 960.0f, viewportHeight = 960.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFe3e3e3)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveToRelative(382.0f, 606.0f)
                lineToRelative(339.0f, -339.0f)
                quadToRelative(12.0f, -12.0f, 28.0f, -12.0f)
                reflectiveQuadToRelative(28.0f, 12.0f)
                quadToRelative(12.0f, 12.0f, 12.0f, 28.5f)
                reflectiveQuadTo(777.0f, 324.0f)
                lineTo(410.0f, 692.0f)
                quadToRelative(-12.0f, 12.0f, -28.0f, 12.0f)
                reflectiveQuadToRelative(-28.0f, -12.0f)
                lineTo(182.0f, 520.0f)
                quadToRelative(-12.0f, -12.0f, -11.5f, -28.5f)
                reflectiveQuadTo(183.0f, 463.0f)
                quadToRelative(12.0f, -12.0f, 28.5f, -12.0f)
                reflectiveQuadToRelative(28.5f, 12.0f)
                lineToRelative(142.0f, 143.0f)
                close()
            }
        }
            .build()
        return _check!!
    }

private var _check: ImageVector? = null
