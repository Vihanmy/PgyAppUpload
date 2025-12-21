package io.github.vihanmy.pgyappupload.model.uistate

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class CmdConfigUiState {
    var name by mutableStateOf("")
    var cmd by mutableStateOf("")
    var workDir by mutableStateOf("")
    var evenStr by mutableStateOf("")

    //
    var isExpend by mutableStateOf(true)

    fun copy(): CmdConfigUiState {
        return CmdConfigUiState().apply {
            this.name = this@CmdConfigUiState.name
            this.cmd = this@CmdConfigUiState.cmd
            this.workDir = this@CmdConfigUiState.workDir
            this.evenStr = this@CmdConfigUiState.evenStr
            this.isExpend = this@CmdConfigUiState.isExpend
        }
    }
}