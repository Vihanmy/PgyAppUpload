package io.github.vihanmy.pgyappupload.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import io.github.vihanmy.pgyappupload.model.processstep.ProcessStepState

@Composable
fun ProcessStepStateIcon(
    state: ProcessStepState
) {
    when (state) {
        ProcessStepState.Progress -> {
            CircularProgressIndicator(
                color = Color.White, // 绿色
                strokeWidth = 4.dp,
                strokeCap = StrokeCap.Round,
                modifier = Modifier.size(20.dp).clip(CircleShape).background(Color(0xffffb51a)).padding(1.dp)
            )
        }

        ProcessStepState.Success -> {
            Icon(
                imageVector = Icons.Default.Check,
                tint = Color.White,
                contentDescription = "success",
                modifier = Modifier.size(20.dp).clip(CircleShape).background(Color(0xff52c41a))
            )
        }

        ProcessStepState.Failed -> {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "success",
                tint = Color.White,
                modifier = Modifier.size(20.dp).clip(CircleShape).background(Color(0xffe84335))
            )
        }
    }
}

@Composable
@Preview
fun PreviewProcessStepStateIcon() {
    ProcessStepStateIcon(ProcessStepState.Progress)
}