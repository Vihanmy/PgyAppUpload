package io.github.vihanmy.pgyappupload.model.processstep

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

open class ProcessStepWithProgress(
    title: String,
    msg: String = "",
) : ProcessStep(title, msg) {
    //0 -> 100
    var progress by mutableStateOf(0f)
}