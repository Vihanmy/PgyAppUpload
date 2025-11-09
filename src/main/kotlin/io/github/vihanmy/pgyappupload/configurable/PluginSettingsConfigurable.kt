package io.github.vihanmy.pgyappupload.configurable

import com.intellij.openapi.options.SearchableConfigurable
import io.github.vihanmy.pgyappupload.jcomponent.PluginSettingsComponent
import io.github.vihanmy.pgyappupload.model.PluginSettingsStateComponent
import javax.swing.JComponent

class PluginSettingsConfigurable : SearchableConfigurable {

    private var settingsComponent: PluginSettingsComponent? = null
    private val settings = PluginSettingsStateComponent.instance

    override fun getId(): String = "io.github.vihanmy.pgyappupload.setting.MyPluginSettingsConfigurable"

    override fun getDisplayName(): String = "蒲公英App上传"

    override fun createComponent(): JComponent {
        val component = PluginSettingsComponent(settings)
        settingsComponent = component
        return component.panel
    }

    override fun isModified(): Boolean {
        return settings.isValueEqualWith(settingsComponent?.settingsState?.toStateComponent()).not()
    }

    override fun apply() {
        settingsComponent?.settingsState?.let {
            settings.loadState(it.toStateComponent())
        }
    }

    override fun reset() {
    }

    override fun disposeUIResources() {
        settingsComponent = null
    }
}
