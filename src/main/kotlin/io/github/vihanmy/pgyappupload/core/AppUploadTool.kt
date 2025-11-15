package io.github.vihanmy.pgyappupload.core

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import io.github.vihanmy.pgyappupload.model.network.ResponseWrapper
import io.github.vihanmy.pgyappupload.model.network.ResultCheckBean
import io.github.vihanmy.pgyappupload.model.network.TokenGetBean
import kotlinx.coroutines.delay
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.*
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class AppUploadTool(
    private val API_KEY_DEV: String = "",
    private val APP_PATH: String = "",
    private val println: (String) -> Unit = {},
    private val progress: (Float) -> Unit = {}
) {


    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    companion object {
        const val SUCCESS_CODE = 0
        const val MAX_CHECK_TIME = 10
        const val CHECK_TIME_ONCE = 3_000L
    }

    suspend fun upload() {

        val tokenBean = getToken(API_KEY_DEV, APP_PATH) ?: return
        val buildKey = tokenBean.params?.key ?: return
        val apiKey = API_KEY_DEV

        println("token获取结果:${tokenBean}")
        val updateFile = updateFile(tokenBean, APP_PATH)
        println("文件上传结果:${updateFile}")

        println("开始检测处理结果")
        var testTime = 1
        var isSuccess = false
        var resultCheckBean: ResultCheckBean? = null
        while (testTime <= MAX_CHECK_TIME) {
            delay(CHECK_TIME_ONCE)
            resultCheckBean = resultCheck(apiKey, buildKey)
            println("检测次数:${testTime} ,检测结果 : ${resultCheckBean != null}")
            if (resultCheckBean != null) {
                isSuccess = true
                break
            }
            testTime++
        }
        println("最终结果:${isSuccess}  查询次数 ${MAX_CHECK_TIME - testTime}")

        if (resultCheckBean != null) {
            println("--------------------------------------------------------------------")
            val gson = GsonBuilder()
                .setPrettyPrinting()
                .create()
            println(gson.toJson(resultCheckBean))
            println("--------------------------------------------------------------------")
        }

    }

    suspend fun resultCheck(apiKey: String, buildKey: String): ResultCheckBean? = suspendCoroutine { continuation ->
        val resultUrl = "https://api.pgyer.com/apiv2/app/buildInfo?_api_key=$apiKey&buildKey=$buildKey"
        //
        val request = Request.Builder()
            .url(resultUrl)
            .get()
            .build()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("onFailure   ${e.message}")
                e.printStackTrace()
                continuation.resume(null)
            }

            override fun onResponse(call: Call, response: Response) {
                try {
                    val bodyStr = response.body?.string()
                    val code = response.code
                    //
                    if ((code != 200) or (bodyStr.isNullOrBlank())) {
                        continuation.resume(null)
                        return
                    }
                    val responseStr = bodyStr
                    val obj = Gson().fromJson<ResponseWrapper<ResultCheckBean>>(
                        responseStr,
                        object : TypeToken<ResponseWrapper<ResultCheckBean>>() {}.type
                    )
                    if (obj.code != SUCCESS_CODE) {
                        continuation.resume(null)
                        return
                    }
                    if (obj.data == null) {
                        continuation.resume(null)
                        return
                    }
                    continuation.resume(obj.data)
                    return
                } catch (e: Exception) {
                    e.printStackTrace()
                    println("Exception ${e.message} ${e.cause}")
                }
                continuation.resume(null)
            }
        })
    }

    suspend fun updateFile(
        tokenBean: TokenGetBean,
        filePath: String,
        progress: (Float) -> Unit = this.progress
    ): Boolean {
        val params = mutableMapOf<String, String>()
        val key = tokenBean.params?.key ?: return false
        val signature = tokenBean.params.signature ?: return false
        val xCosSecurityToken = tokenBean.params.xCosSecurityToken ?: return false

        params["key"] = key
        params["signature"] = signature
        params["x-cos-security-token"] = xCosSecurityToken

        val url = tokenBean.endpoint ?: return false
        val file = File(filePath) // 应用文件路径

        // 构建多部分表单
        val requestBodyBuilder = MultipartBody.Builder()
            .setType(MultipartBody.FORM)

        // 添加参数
        params.forEach { (key, value) ->
            requestBodyBuilder.addFormDataPart(key, value)
        }


        val up = OkHttpFileUploader()
        val updateResult = up.uploadFileWithProgress(url, file, params) { bytesWritten, contentLength, progress ->
            //println("上传进度 :${progress}")
            progress(progress)
        }

        return updateResult?.code == 204
    }

    suspend fun fetchToken(): TokenGetBean? {
        return getToken(API_KEY_DEV, APP_PATH)
    }

    suspend fun getToken(
        apiKey: String,
        filePath: String,
    ): TokenGetBean? = suspendCancellableCoroutine { cont ->
        val params = mutableMapOf<String, String>()
        params["_api_key"] = apiKey
        val buildInstallType = 1
        params["buildInstallType"] = "$buildInstallType" // buildInstallType 1,2,3，默认为1 公开安装
        if (buildInstallType == 2) {
            params["buildPassword"] = "" // 需要安装密码
        }
        params["buildUpdateDescription"] = "-" // 版本更新日志

        val uploadFile = File(filePath) // 应用文件路径

        // 根据文件扩展名确定 buildType
        val fileName = uploadFile.name.lowercase()
        val buildType = when {
            fileName.endsWith(".ipa") -> "ios"
            fileName.endsWith(".apk") -> "android"
            fileName.endsWith(".hap") -> "harmony"
            else -> throw IllegalArgumentException("Unsupported file type. Supported types: ipa, apk, hap")
        }
        params["buildType"] = buildType


        val requestBodyBuilder = MultipartBody.Builder().setType(MultipartBody.FORM)

        params.forEach { (key, value) ->
            if (key == "buildUpdateDescription") {
                if (value.isBlank().not()) {
                    requestBodyBuilder.addFormDataPart(key, value)
                }
            } else {
                requestBodyBuilder.addFormDataPart(key, value)
            }
        }

        val request = Request.Builder()
            .url("https://api.pgyer.com/apiv2/app/getCOSToken")
            .post(requestBodyBuilder.build())
            .build()

        okHttpClient.newCall(request).execute().use { response ->
            if (response.code == 200) {
                val responseString = response.body?.string() ?: ""
                try {
                    val obj = Gson().fromJson<ResponseWrapper<TokenGetBean>>(
                        responseString,
                        object : TypeToken<ResponseWrapper<TokenGetBean>>() {}.type
                    )
                    cont.resume(obj.data)
                } catch (e: Exception) {
                    e.printStackTrace()
                    cont.resume(null)
                }

            } else {
                cont.resume(null)
            }
        }

    }
}


