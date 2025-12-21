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

public val AppIcons.MoveToInbox: ImageVector
    get() {
        if (_moveToInbox != null) {
            return _moveToInbox!!
        }
        _moveToInbox = Builder(
            name = "MoveToInbox", defaultWidth = 24.0.dp, defaultHeight =
                24.0.dp, viewportWidth = 960.0f, viewportHeight = 960.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFe3e3e3)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(440.0f, 406.0f)
                verticalLineToRelative(-126.0f)
                quadToRelative(0.0f, -17.0f, 11.5f, -28.5f)
                reflectiveQuadTo(480.0f, 240.0f)
                quadToRelative(17.0f, 0.0f, 28.5f, 11.5f)
                reflectiveQuadTo(520.0f, 280.0f)
                verticalLineToRelative(126.0f)
                lineToRelative(35.0f, -35.0f)
                quadToRelative(6.0f, -6.0f, 13.5f, -9.0f)
                reflectiveQuadToRelative(15.0f, -2.5f)
                quadToRelative(7.5f, 0.5f, 15.0f, 3.5f)
                reflectiveQuadToRelative(13.5f, 9.0f)
                quadToRelative(11.0f, 12.0f, 11.5f, 28.0f)
                reflectiveQuadTo(612.0f, 428.0f)
                lineTo(508.0f, 532.0f)
                quadToRelative(-6.0f, 6.0f, -13.0f, 8.5f)
                reflectiveQuadToRelative(-15.0f, 2.5f)
                quadToRelative(-8.0f, 0.0f, -15.0f, -2.5f)
                reflectiveQuadToRelative(-13.0f, -8.5f)
                lineTo(348.0f, 428.0f)
                quadToRelative(-6.0f, -6.0f, -9.0f, -13.5f)
                reflectiveQuadToRelative(-3.0f, -15.0f)
                quadToRelative(0.0f, -7.5f, 3.0f, -14.5f)
                reflectiveQuadToRelative(9.0f, -13.0f)
                quadToRelative(12.0f, -12.0f, 28.5f, -12.5f)
                reflectiveQuadTo(405.0f, 371.0f)
                lineToRelative(35.0f, 35.0f)
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
                verticalLineToRelative(560.0f)
                quadToRelative(0.0f, 33.0f, -23.5f, 56.5f)
                reflectiveQuadTo(760.0f, 840.0f)
                lineTo(200.0f, 840.0f)
                close()
                moveTo(200.0f, 760.0f)
                horizontalLineToRelative(560.0f)
                verticalLineToRelative(-120.0f)
                lineTo(640.0f, 640.0f)
                quadToRelative(-30.0f, 38.0f, -71.5f, 59.0f)
                reflectiveQuadTo(480.0f, 720.0f)
                quadToRelative(-47.0f, 0.0f, -88.5f, -21.0f)
                reflectiveQuadTo(320.0f, 640.0f)
                lineTo(200.0f, 640.0f)
                verticalLineToRelative(120.0f)
                close()
                moveTo(480.0f, 640.0f)
                quadToRelative(32.0f, 0.0f, 59.0f, -16.5f)
                reflectiveQuadToRelative(44.0f, -43.5f)
                quadToRelative(6.0f, -9.0f, 15.0f, -14.5f)
                reflectiveQuadToRelative(20.0f, -5.5f)
                horizontalLineToRelative(142.0f)
                verticalLineToRelative(-360.0f)
                lineTo(200.0f, 200.0f)
                verticalLineToRelative(360.0f)
                horizontalLineToRelative(142.0f)
                quadToRelative(11.0f, 0.0f, 20.0f, 5.5f)
                reflectiveQuadToRelative(15.0f, 14.5f)
                quadToRelative(17.0f, 27.0f, 44.0f, 43.5f)
                reflectiveQuadToRelative(59.0f, 16.5f)
                close()
                moveTo(200.0f, 760.0f)
                horizontalLineToRelative(560.0f)
                horizontalLineToRelative(-560.0f)
                close()
            }
        }
            .build()
        return _moveToInbox!!
    }

private var _moveToInbox: ImageVector? = null
