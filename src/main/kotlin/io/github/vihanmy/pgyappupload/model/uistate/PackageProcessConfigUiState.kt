package io.github.vihanmy.pgyappupload.model.uistate

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class PackageProcessConfigUiState {
    var cmdList = mutableStateListOf<CmdConfigUiState>()
    var packageOutPutPath by mutableStateOf("")
    var name by mutableStateOf("")

    //
    var isExpend by mutableStateOf(false)

    fun copy(): PackageProcessConfigUiState {
        return PackageProcessConfigUiState().apply {
            this.packageOutPutPath = this@PackageProcessConfigUiState.packageOutPutPath
            this.name = this@PackageProcessConfigUiState.name
            this.isExpend = this@PackageProcessConfigUiState.isExpend
            this.cmdList.addAll(this@PackageProcessConfigUiState.cmdList.map { it.copy() })
        }
    }
}