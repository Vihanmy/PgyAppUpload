package io.github.vihanmy.pgyappupload.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.onClick
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UIPureIconButton(
    icon: ImageVector,
    conClick: () -> Unit
) {
    Icon(
        imageVector = icon,
        tint = MaterialTheme.colors.onBackground.copy(alpha = 0.9f),
        contentDescription = "",
        modifier = Modifier
            .height(28.dp)
            .width(28.dp)
            .border(2.dp, MaterialTheme.colors.onBackground.copy(0.1f), RoundedCornerShape(6.dp))
            .padding(4.dp)
            .onClick {
                conClick()
            }
    )
}