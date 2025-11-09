package io.github.vihanmy.pgyappupload.model.uistate

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import io.github.vihanmy.pgyappupload.model.PluginSettingsStateComponent

class PluginSettingsUiState {
    var pgyApiKey by mutableStateOf("")

    fun updateFromStateComponent(state: PluginSettingsStateComponent) {
        this.pgyApiKey = state.pgyApiKey
    }

    fun toStateComponent(): PluginSettingsStateComponent {
        return PluginSettingsStateComponent().apply {
            this.pgyApiKey = this@PluginSettingsUiState.pgyApiKey
        }
    }
}