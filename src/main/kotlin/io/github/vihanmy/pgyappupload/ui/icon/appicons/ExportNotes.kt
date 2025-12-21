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

public val AppIcons.ExportNotes: ImageVector
    get() {
        if (_exportNotes != null) {
            return _exportNotes!!
        }
        _exportNotes = Builder(
            name = "ExportNotes", defaultWidth = 24.0.dp, defaultHeight =
                24.0.dp, viewportWidth = 960.0f, viewportHeight = 960.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFe3e3e3)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(760.0f, 708.0f)
                verticalLineToRelative(72.0f)
                quadToRelative(0.0f, 8.0f, 6.0f, 14.0f)
                reflectiveQuadToRelative(14.0f, 6.0f)
                quadToRelative(8.0f, 0.0f, 14.0f, -6.0f)
                reflectiveQuadToRelative(6.0f, -14.0f)
                verticalLineToRelative(-120.0f)
                quadToRelative(0.0f, -8.0f, -6.0f, -14.0f)
                reflectiveQuadToRelative(-14.0f, -6.0f)
                lineTo(660.0f, 640.0f)
                quadToRelative(-8.0f, 0.0f, -14.0f, 6.0f)
                reflectiveQuadToRelative(-6.0f, 14.0f)
                quadToRelative(0.0f, 8.0f, 6.0f, 14.0f)
                reflectiveQuadToRelative(14.0f, 6.0f)
                horizontalLineToRelative(72.0f)
                lineToRelative(-98.0f, 98.0f)
                quadToRelative(-6.0f, 6.0f, -6.0f, 14.0f)
                reflectiveQuadToRelative(6.0f, 14.0f)
                quadToRelative(6.0f, 6.0f, 14.0f, 6.0f)
                reflectiveQuadToRelative(14.0f, -6.0f)
                lineToRelative(98.0f, -98.0f)
                close()
                moveTo(200.0f, 840.0f)
                quadToRelative(-33.0f, 0.0f, -56.5f, -23.5f)
                reflectiveQuadTo(120.0f, 760.0f)
                verticalLineToRelative(-560.0f)
                quadToRelative(0.0f, -33.0f, 23.5f, -56.5f)
                reflectiveQuadTo(200.0f, 120.0f)
                horizontalLineToRelative(560.0f)
                quadToRelative(33.0f, 0.0f, 56.5f, 23.5f)
                reflectiveQuadTo(840.0f, 200.0f)
                verticalLineToRelative(200.0f)
                quadToRelative(0.0f, 17.0f, -11.5f, 28.5f)
                reflectiveQuadTo(800.0f, 440.0f)
                quadToRelative(-17.0f, 0.0f, -28.5f, -11.5f)
                reflectiveQuadTo(760.0f, 400.0f)
                verticalLineToRelative(-200.0f)
                lineTo(200.0f, 200.0f)
                verticalLineToRelative(560.0f)
                horizontalLineToRelative(200.0f)
                quadToRelative(17.0f, 0.0f, 28.5f, 11.5f)
                reflectiveQuadTo(440.0f, 800.0f)
                quadToRelative(0.0f, 17.0f, -11.5f, 28.5f)
                reflectiveQuadTo(400.0f, 840.0f)
                lineTo(200.0f, 840.0f)
                close()
                moveTo(200.0f, 720.0f)
                verticalLineToRelative(40.0f)
                verticalLineToRelative(-560.0f)
                verticalLineToRelative(243.0f)
                verticalLineToRelative(-3.0f)
                verticalLineToRelative(280.0f)
                close()
                moveTo(280.0f, 640.0f)
                quadToRelative(0.0f, 17.0f, 11.5f, 28.5f)
                reflectiveQuadTo(320.0f, 680.0f)
                horizontalLineToRelative(83.0f)
                quadToRelative(17.0f, 0.0f, 28.5f, -11.5f)
                reflectiveQuadTo(443.0f, 640.0f)
                quadToRelative(0.0f, -17.0f, -11.5f, -28.5f)
                reflectiveQuadTo(403.0f, 600.0f)
                horizontalLineToRelative(-83.0f)
                quadToRelative(-17.0f, 0.0f, -28.5f, 11.5f)
                reflectiveQuadTo(280.0f, 640.0f)
                close()
                moveTo(280.0f, 480.0f)
                quadToRelative(0.0f, 17.0f, 11.5f, 28.5f)
                reflectiveQuadTo(320.0f, 520.0f)
                horizontalLineToRelative(200.0f)
                quadToRelative(17.0f, 0.0f, 28.5f, -11.5f)
                reflectiveQuadTo(560.0f, 480.0f)
                quadToRelative(0.0f, -17.0f, -11.5f, -28.5f)
                reflectiveQuadTo(520.0f, 440.0f)
                lineTo(320.0f, 440.0f)
                quadToRelative(-17.0f, 0.0f, -28.5f, 11.5f)
                reflectiveQuadTo(280.0f, 480.0f)
                close()
                moveTo(280.0f, 320.0f)
                quadToRelative(0.0f, 17.0f, 11.5f, 28.5f)
                reflectiveQuadTo(320.0f, 360.0f)
                horizontalLineToRelative(320.0f)
                quadToRelative(17.0f, 0.0f, 28.5f, -11.5f)
                reflectiveQuadTo(680.0f, 320.0f)
                quadToRelative(0.0f, -17.0f, -11.5f, -28.5f)
                reflectiveQuadTo(640.0f, 280.0f)
                lineTo(320.0f, 280.0f)
                quadToRelative(-17.0f, 0.0f, -28.5f, 11.5f)
                reflectiveQuadTo(280.0f, 320.0f)
                close()
                moveTo(720.0f, 920.0f)
                quadToRelative(-83.0f, 0.0f, -141.5f, -58.5f)
                reflectiveQuadTo(520.0f, 720.0f)
                quadToRelative(0.0f, -83.0f, 58.5f, -141.5f)
                reflectiveQuadTo(720.0f, 520.0f)
                quadToRelative(83.0f, 0.0f, 141.5f, 58.5f)
                reflectiveQuadTo(920.0f, 720.0f)
                quadToRelative(0.0f, 83.0f, -58.5f, 141.5f)
                reflectiveQuadTo(720.0f, 920.0f)
                close()
            }
        }
            .build()
        return _exportNotes!!
    }

private var _exportNotes: ImageVector? = null

