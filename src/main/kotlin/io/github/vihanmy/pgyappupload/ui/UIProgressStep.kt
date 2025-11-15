package io.github.vihanmy.pgyappupload.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.vihanmy.pgyappupload.model.processstep.ProcessStep
import io.github.vihanmy.pgyappupload.model.processstep.ProcessStepState
import io.github.vihanmy.pgyappupload.model.processstep.ProcessStepWithProgress

@Preview
@Composable
fun UIProgressStepPreView() {
    UIProgressStep(ProcessStep("某个步骤", "").apply {
        state = ProcessStepState.Success
        msg = "步骤描述文本"
        showStepLine = true
    })
}

@Composable
fun UIProgressStep(
    step: ProcessStep,
) {
    var msgHeight by remember { mutableStateOf(0) }
    val density = LocalDensity.current

    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            ProcessStepStateIcon(step.state)//20
            Text(step.title, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 4.dp), fontSize = 16.sp)
        }
        Row {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.width(20.dp).heightIn(min = 30.dp)
            ) {
                if (step.showStepLine) {
                    Box(
                        modifier = Modifier.width(3.dp).height(
                            ((with(density) { msgHeight.toDp() }.value + 4 + 10).coerceAtLeast(30f)).dp
                        ).clip(RoundedCornerShape(5.dp)).background(Color(0xff7cbe9c)),
                    )
                }
            }

            Column(
                modifier = Modifier
                    .padding(top = 4.dp, start = 4.dp, bottom = 10.dp)
                    .onGloballyPositioned { coordinates ->
                        msgHeight = coordinates.size.height
                    },
            ) {
                //标题
                if (step.msg.isNotBlank()) {
                    Box(
                        modifier = Modifier
                            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                            .background(Color.White, RoundedCornerShape(8.dp))
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            SelectionContainer {
                                Text(step.msg)
                            }
                            if (step is ProcessStepWithProgress) {
                                if (step.progress > 0) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        LinearProgressIndicator(progress = step.progress / 100f, modifier = Modifier.height(10.dp).clip(CircleShape).weight(2f))
                                        Text(" (${step.progress.toInt()}%)", maxLines = 1, modifier = Modifier.weight(1f, fill = false))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}