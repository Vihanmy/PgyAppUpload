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

public val AppIcons.Save: ImageVector
    get() {
        if (_save != null) {
            return _save!!
        }
        _save = Builder(
            name = "Save", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
            viewportWidth = 960.0f, viewportHeight = 960.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFe3e3e3)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(200.0f, 840.0f)
                quadToRelative(-33.0f, 0.0f, -56.5f, -23.5f)
                reflectiveQuadTo(120.0f, 760.0f)
                verticalLineToRelative(-560.0f)
                quadToRelative(0.0f, -33.0f, 23.5f, -56.5f)
                reflectiveQuadTo(200.0f, 120.0f)
                horizontalLineToRelative(447.0f)
                quadToRelative(16.0f, 0.0f, 30.5f, 6.0f)
                reflectiveQuadToRelative(25.5f, 17.0f)
                lineToRelative(114.0f, 114.0f)
                quadToRelative(11.0f, 11.0f, 17.0f, 25.5f)
                reflectiveQuadToRelative(6.0f, 30.5f)
                verticalLineToRelative(447.0f)
                quadToRelative(0.0f, 33.0f, -23.5f, 56.5f)
                reflectiveQuadTo(760.0f, 840.0f)
                lineTo(200.0f, 840.0f)
                close()
                moveTo(760.0f, 314.0f)
                lineTo(646.0f, 200.0f)
                lineTo(200.0f, 200.0f)
                verticalLineToRelative(560.0f)
                horizontalLineToRelative(560.0f)
                verticalLineToRelative(-446.0f)
                close()
                moveTo(480.0f, 720.0f)
                quadToRelative(50.0f, 0.0f, 85.0f, -35.0f)
                reflectiveQuadToRelative(35.0f, -85.0f)
                quadToRelative(0.0f, -50.0f, -35.0f, -85.0f)
                reflectiveQuadToRelative(-85.0f, -35.0f)
                quadToRelative(-50.0f, 0.0f, -85.0f, 35.0f)
                reflectiveQuadToRelative(-35.0f, 85.0f)
                quadToRelative(0.0f, 50.0f, 35.0f, 85.0f)
                reflectiveQuadToRelative(85.0f, 35.0f)
                close()
                moveTo(280.0f, 400.0f)
                horizontalLineToRelative(280.0f)
                quadToRelative(17.0f, 0.0f, 28.5f, -11.5f)
                reflectiveQuadTo(600.0f, 360.0f)
                verticalLineToRelative(-80.0f)
                quadToRelative(0.0f, -17.0f, -11.5f, -28.5f)
                reflectiveQuadTo(560.0f, 240.0f)
                lineTo(280.0f, 240.0f)
                quadToRelative(-17.0f, 0.0f, -28.5f, 11.5f)
                reflectiveQuadTo(240.0f, 280.0f)
                verticalLineToRelative(80.0f)
                quadToRelative(0.0f, 17.0f, 11.5f, 28.5f)
                reflectiveQuadTo(280.0f, 400.0f)
                close()
                moveTo(200.0f, 314.0f)
                verticalLineToRelative(446.0f)
                verticalLineToRelative(-560.0f)
                verticalLineToRelative(114.0f)
                close()
            }
        }
            .build()
        return _save!!
    }

private var _save: ImageVector? = null