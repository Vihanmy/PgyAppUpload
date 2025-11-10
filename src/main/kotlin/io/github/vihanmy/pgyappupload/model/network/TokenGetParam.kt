package io.github.vihanmy.pgyappupload.model.network

import com.google.gson.annotations.SerializedName

data class TokenGetParam(
    val signature: String?,
    @SerializedName("x-cos-security-token")
    val xCosSecurityToken: String?,
    val key: String?,
)