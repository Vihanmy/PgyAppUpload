package io.github.vihanmy.pgyappupload.model

class PackageProcessConfig {
    var cmdList: MutableList<CmdConfig> = mutableListOf()
    var packageOutPutPath: String = ""
    var name: String = ""

    fun contentEqual(other: PackageProcessConfig?): Boolean {
        if (other == null) return false
        if (packageOutPutPath != other.packageOutPutPath) return false
        if (name != other.name) return false
        if (cmdList.size != other.cmdList.size) return false
        cmdList.forEachIndexed { index, cmdConfig ->
            if (cmdConfig != other.cmdList[index]) return false
        }
        return true
    }
}