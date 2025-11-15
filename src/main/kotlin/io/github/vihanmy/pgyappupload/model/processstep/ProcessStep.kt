package io.github.vihanmy.pgyappupload.model.processstep

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

open class ProcessStep(
    title: String,
    msg: String = "",
) {
    var state by mutableStateOf(ProcessStepState.Progress)
    var title by mutableStateOf(title)
    var msg by mutableStateOf(msg)
    var showStepLine by mutableStateOf(false)

    fun markState(isSuccess: Boolean, msg: String = "") {
        state = if (isSuccess) ProcessStepState.Success else ProcessStepState.Failed
        this.msg = msg
    }
}