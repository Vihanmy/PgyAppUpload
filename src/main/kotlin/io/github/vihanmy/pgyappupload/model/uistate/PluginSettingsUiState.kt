package io.github.vihanmy.pgyappupload.model.uistate

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import io.github.vihanmy.pgyappupload.model.CmdConfig
import io.github.vihanmy.pgyappupload.model.PackageProcessConfig
import io.github.vihanmy.pgyappupload.model.PluginSettingsStateComponent

class PluginSettingsUiState {
    var pgyApiKey by mutableStateOf("")
    var packageProcessConfigList = mutableStateListOf<PackageProcessConfigUiState>()

    fun updateFromStateComponent(state: PluginSettingsStateComponent) {
        this.pgyApiKey = state.pgyApiKey
        this.packageProcessConfigList.addAll(state.packageProcessConfigList.map { packageProcessConfig ->
            PackageProcessConfigUiState().apply {
                packageOutPutPath = packageProcessConfig.packageOutPutPath
                name = packageProcessConfig.name
                cmdList.addAll(packageProcessConfig.cmdList.map { cmdConfig ->
                    CmdConfigUiState().apply {
                        name = cmdConfig.name
                        cmd = cmdConfig.cmd
                        workDir = cmdConfig.workDir
                        evenStr = cmdConfig.evenStr
                    }
                })
            }
        })
    }

    fun toStateComponent(): PluginSettingsStateComponent {
        return PluginSettingsStateComponent().apply {
            this.pgyApiKey = this@PluginSettingsUiState.pgyApiKey
            this.packageProcessConfigList.clear()
            this.packageProcessConfigList.addAll(
                this@PluginSettingsUiState.packageProcessConfigList.map { item ->
                    PackageProcessConfig().apply {
                        packageOutPutPath = item.packageOutPutPath
                        name = item.name
                        cmdList.clear()
                        cmdList.addAll(
                            item.cmdList.map { cmdConfig ->
                                CmdConfig().apply {
                                    name = cmdConfig.name
                                    cmd = cmdConfig.cmd
                                    workDir = cmdConfig.workDir
                                    evenStr = cmdConfig.evenStr
                                }
                            }
                        )
                    }
                }
            )
        }
    }
}