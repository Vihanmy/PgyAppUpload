package io.github.vihanmy.pgyappupload.model.network

import com.google.gson.annotations.SerializedName

data class ResultCheckBean(
    val buildKey: String?,
    val buildType: String?,
    val buildIsFirst: String?,
    val buildIsLastest: String?,
    val buildFileKey: String?,
    val buildFileName: String?,
    val buildFileSize: String?,
    val buildName: String?,
    val buildVersion: String?,
    val buildVersionNo: String?,
    val buildBuildVersion: String?,
    val buildIdentifier: String?,
    val buildIcon: String?,
    val buildDescription: String?,
    val buildUpdateDescription: String?,
    val buildScreenshots: String?,
    val buildShortcutUrl: String?,
    val buildCreated: String?,
    val buildUpdated: String?,
    @SerializedName("buildQRCodeURL")
    val buildQrcodeUrl: String?,
)