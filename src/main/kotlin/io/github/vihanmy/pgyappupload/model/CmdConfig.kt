package io.github.vihanmy.pgyappupload.model

class CmdConfig {
    var cmd: String = ""
    var workDir: String = ""
    var evenStr: String = ""


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CmdConfig

        if (cmd != other.cmd) return false
        if (workDir != other.workDir) return false
        if (evenStr != other.evenStr) return false

        return true
    }

}