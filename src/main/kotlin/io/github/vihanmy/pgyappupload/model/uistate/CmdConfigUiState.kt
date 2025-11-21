package io.github.vihanmy.pgyappupload.model.uistate

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class CmdConfigUiState {
    var cmd by mutableStateOf("")
    var workDir by mutableStateOf("")
    var evenStr by mutableStateOf("")
}