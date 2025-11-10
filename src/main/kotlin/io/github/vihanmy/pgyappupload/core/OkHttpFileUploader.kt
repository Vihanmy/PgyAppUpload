package io.github.vihanmy.pgyappupload.core

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.asRequestBody
import okio.Buffer
import okio.BufferedSink
import okio.ForwardingSink
import okio.buffer
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class OkHttpFileUploader {

    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    suspend fun uploadFileWithProgress(
        url: String,
        file: File,
        params: Map<String, String> = emptyMap(),
        onProgress: (bytesWritten: Long, contentLength: Long, progress: Float) -> Unit
    ) = suspendCoroutine<Response?> { cont ->
        val fileBody = file.asRequestBody("application/octet-stream".toMediaTypeOrNull())

        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .apply {
                params.forEach { (key, value) ->
                    addFormDataPart(key, value)
                }
                addFormDataPart("file", file.name, fileBody)
            }
            .build()

        // 包装请求体以监听进度
        val progressRequestBody = ProgressRequestBody(requestBody, onProgress)

        val request = Request.Builder()
            .url(url)
            .post(progressRequestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                cont.resume(null)
            }

            override fun onResponse(call: Call, response: Response) {
                cont.resume(response)
            }

        })
    }

    inner class ProgressRequestBody(
        private val requestBody: RequestBody,
        private val onProgress: (bytesWritten: Long, contentLength: Long, progress: Float) -> Unit
    ) : RequestBody() {

        override fun contentType(): MediaType? = requestBody.contentType()

        override fun contentLength(): Long = requestBody.contentLength()

        override fun writeTo(sink: BufferedSink) {
            val contentLength = contentLength()
            val bufferedSink = object : ForwardingSink(sink) {
                private var bytesWritten = 0L

                override fun write(source: Buffer, byteCount: Long) {
                    super.write(source, byteCount)
                    bytesWritten += byteCount

                    val progress = if (contentLength > 0) {
                        (bytesWritten.toFloat() / contentLength) * 100
                    } else {
                        0f
                    }

                    onProgress(bytesWritten, contentLength, progress)
                }
            }.buffer()

            requestBody.writeTo(bufferedSink)
            bufferedSink.flush()
        }
    }
}