package io.github.vihanmy.pgyappupload.model.network

data class ResponseWrapper<T>(
    val code: Int?,
    val message: String?,
    val data: T?,
)