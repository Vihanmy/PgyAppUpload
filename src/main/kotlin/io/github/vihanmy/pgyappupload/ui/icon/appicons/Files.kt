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

public val AppIcons.Files: ImageVector
    get() {
        if (_files != null) {
            return _files!!
        }
        _files = Builder(
            name = "Files", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
            viewportWidth = 960.0f, viewportHeight = 960.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFe3e3e3)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(160.0f, 800.0f)
                quadToRelative(-33.0f, 0.0f, -56.5f, -23.5f)
                reflectiveQuadTo(80.0f, 720.0f)
                verticalLineToRelative(-400.0f)
                quadToRelative(0.0f, -33.0f, 23.5f, -56.5f)
                reflectiveQuadTo(160.0f, 240.0f)
                horizontalLineToRelative(240.0f)
                lineToRelative(80.0f, -80.0f)
                horizontalLineToRelative(320.0f)
                quadToRelative(33.0f, 0.0f, 56.5f, 23.5f)
                reflectiveQuadTo(880.0f, 240.0f)
                verticalLineToRelative(480.0f)
                quadToRelative(0.0f, 33.0f, -23.5f, 56.5f)
                reflectiveQuadTo(800.0f, 800.0f)
                lineTo(160.0f, 800.0f)
                close()
                moveTo(233.0f, 520.0f)
                horizontalLineToRelative(207.0f)
                verticalLineToRelative(-207.0f)
                lineTo(233.0f, 520.0f)
                close()
                moveTo(160.0f, 480.0f)
                lineTo(320.0f, 320.0f)
                lineTo(160.0f, 320.0f)
                verticalLineToRelative(160.0f)
                close()
                moveTo(160.0f, 600.0f)
                verticalLineToRelative(120.0f)
                horizontalLineToRelative(640.0f)
                verticalLineToRelative(-480.0f)
                lineTo(520.0f, 240.0f)
                verticalLineToRelative(280.0f)
                quadToRelative(0.0f, 33.0f, -23.5f, 56.5f)
                reflectiveQuadTo(440.0f, 600.0f)
                lineTo(160.0f, 600.0f)
                close()
                moveTo(440.0f, 440.0f)
                close()
            }
        }
            .build()
        return _files!!
    }

private var _files: ImageVector? = null

