package io.github.vihanmy.pgyappupload.model

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil

@State(
    name = "PgyAppUploadSettings",
    storages = [Storage("PgyAppUploadSettings.xml")]
)
@Service(Service.Level.APP)
class PluginSettingsStateComponent : PersistentStateComponent<PluginSettingsStateComponent> {

    companion object {
        val instance: PluginSettingsStateComponent
            get() = ApplicationManager.getApplication().getService(PluginSettingsStateComponent::class.java)
    }

    override fun getState(): PluginSettingsStateComponent = this

    override fun loadState(state: PluginSettingsStateComponent) {
        XmlSerializerUtil.copyBean(state, this)
    }

    fun isValueEqualWith(other: PluginSettingsStateComponent?): Boolean {
        if (other == null) return false
        if (other.pgyApiKey != pgyApiKey) return false
        if (other.packageProcessConfigList.size != packageProcessConfigList.size) return false

        other.packageProcessConfigList.forEachIndexed { index, packageProcessConfig ->
            if (packageProcessConfigList[index].contentEqual(packageProcessConfig).not()) return false
        }

        return true
    }

    ///////////////////////////////////////////////////////// paras
    var pgyApiKey: String = ""
    var packageProcessConfigList: MutableList<PackageProcessConfig> = mutableListOf()

}