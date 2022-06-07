package com.xlhy.lgs.utils

import com.lzy.okgo.OkGo
import com.lzy.okgo.cache.CacheMode
import com.lzy.okgo.callback.Callback
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.HttpHeaders
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.model.Response
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import java.io.File


/**
 * @Description 访问网络工具类
 * @Author linguangshuo
 * @Date 05-11-2022 周三 11:13
 */

typealias onSuccess<T> = (String) -> T
typealias onFailed<T> = (String) -> T

object LGSHttpUtils {
    fun get(
        url: String,
        block: onSuccess<Unit>? = null,
        errorBlock: onFailed<Unit>? = null
    ) {
        get(url, null, null, block, errorBlock)
    }

    fun get(
        url: String,
        params: Map<String, String>,
        block: onSuccess<Unit>? = null,
        errorBlock: onFailed<Unit>? = null
    ) {
        get(url, null, params, block, errorBlock)
    }

    fun get(
        headers: Map<String, String>,
        url: String,
        block: onSuccess<Unit>,
        errorBlock: onFailed<Unit>
    ) {
        get(url, headers, null, block, errorBlock)
    }

    fun get(
        url: String,
        headers: Map<String, String>?,
        params: Map<String, String>?,
        block: onSuccess<Unit>? = null,
        errorBlock: onFailed<Unit>? = null
    ) {
        val httpHeaders = HttpHeaders()
        headers?.takeIf { it.isNotEmpty() }?.forEach {
            httpHeaders.put(it.key, it.value)
        }

        val httpParams = HttpParams()
        params?.takeIf { it.isNotEmpty() }?.forEach {
            httpParams.put(it.key, it.value)
        }

        get(url, httpHeaders, httpParams, block, errorBlock)
    }

    /**
     * @param url   请求地址
     * @param httpHeaders  请求头
     * @param httpParams   请求参数
     * @param block     请求成功后调用的方法,参数为response.body()
     * @param errorBlock    请求失败后调用的方法
     */
    fun get(
        url: String,
        httpHeaders: HttpHeaders?,
        httpParams: HttpParams,
        block: onSuccess<Unit>? = null,
        errorBlock: onFailed<Unit>? = null
    ) {
        OkGo.get<String>(url)
            .tag(this)
            .headers(httpHeaders)
            .params(httpParams)
            .execute(object : StringCallback() {
                override fun onSuccess(response: Response<String>?) {
                    try {
                        response?.body()?.let {
                            block?.invoke(it)
                            return
                        }
                        errorBlock?.invoke("返回内容为空")
                    } catch (e: Exception) {
                        errorBlock?.invoke(e.stackTrace.toString())
                    }
                }

                override fun onError(response: Response<String>?) {
                    super.onError(response)
                    try {
                        response?.rawResponse?.message()?.let {
                            errorBlock?.invoke(it)
                            return
                        }
                        errorBlock?.invoke("网络错误")
                    } catch (e: Exception) {
                        errorBlock?.invoke(e.stackTrace.toString())
                    }
                }
            })
    }

    fun post(
        url: String,
        block: onSuccess<Unit>? = null,
        errorBlock: onFailed<Unit>? = null
    ) {
        post(url, null, null, block, errorBlock)
    }

    fun post(
        url: String,
        params: Map<String, String>,
        block: onSuccess<Unit>? = null,
        errorBlock: onFailed<Unit>? = null,
        cacheMode: CacheMode = CacheMode.REQUEST_FAILED_READ_CACHE
    ) {
        post(url, null, params, block, errorBlock, cacheMode)
    }

    fun post(
        headers: Map<String, String>,
        url: String,
        block: onSuccess<Unit>,
        errorBlock: onFailed<Unit>
    ) {
        post(url, headers, null, block, errorBlock)
    }

    fun post(
        url: String,
        headers: Map<String, String>?,
        params: Map<String, String>?,
        block: onSuccess<Unit>? = null,
        errorBlock: onFailed<Unit>? = null,
        cacheMode: CacheMode = CacheMode.REQUEST_FAILED_READ_CACHE
    ) {
        val httpHeaders = HttpHeaders()
        headers?.takeIf { it.isNotEmpty() }?.forEach {
            httpHeaders.put(it.key, it.value)
        }

        val httpParams = HttpParams()
        params?.takeIf { it.isNotEmpty() }?.forEach {
            httpParams.put(it.key, it.value)
        }

        post(url, httpHeaders, httpParams, block, errorBlock, cacheMode)
    }

    /**
     * @param url   请求地址
     * @param httpHeaders  请求头
     * @param httpParams   请求参数
     * @param block     请求成功后调用的方法,参数为response.body()
     * @param errorBlock    请求失败后调用的方法
     */
    fun post(
        url: String,
        httpHeaders: HttpHeaders?,
        httpParams: HttpParams,
        block: onSuccess<Unit>? = null,
        errorBlock: onFailed<Unit>? = null,
        cacheMode: CacheMode = CacheMode.REQUEST_FAILED_READ_CACHE
    ) {
        OkGo.post<String>(url)
            .tag(this)
            .cacheMode(cacheMode)
            .headers(httpHeaders)
            .params(httpParams)
            .execute(object : StringCallback() {
                override fun onSuccess(response: Response<String>?) {
                    try {
                        response?.body()?.let {
                            block?.invoke(it)
                            return
                        }
                        errorBlock?.invoke("返回内容为空")
                    } catch (e: Exception) {
                        errorBlock?.invoke(e.stackTrace.toString())
                    }
                }

                override fun onCacheSuccess(response: Response<String>?) {
                    try {
                        response?.body()?.let {
                            block?.invoke(it)
                            return
                        }
                        errorBlock?.invoke("返回内容为空")
                    } catch (e: Exception) {
                        errorBlock?.invoke(e.stackTrace.toString())
                    }
                }

                override fun onError(response: Response<String>?) {
                    super.onError(response)
                    try {
                        response?.rawResponse?.message()?.let {
                            errorBlock?.invoke(it)
                            return
                        }
                        errorBlock?.invoke("网络错误")
                    } catch (e: Exception) {
                        errorBlock?.invoke(e.stackTrace.toString())
                    }
                }
            })
    }

    fun postFormData(
        url: String,
        block: onSuccess<Unit>? = null,
        errorBlock: onFailed<Unit>? = null
    ) {
        postFormData(url, null, null, block, errorBlock)
    }

    fun postFormData(
        url: String,
        params: Map<String, String>,
        block: onSuccess<Unit>? = null,
        errorBlock: onFailed<Unit>? = null
    ) {
        postFormData(url, null, params, block, errorBlock)
    }

    fun postFormData(
        headers: Map<String, String>,
        url: String,
        block: onSuccess<Unit>,
        errorBlock: onFailed<Unit>
    ) {
        postFormData(url, headers, null, block, errorBlock)
    }

    fun postFormData(
        url: String,
        headers: Map<String, String>?,
        params: Map<String, String>?,
        block: onSuccess<Unit>? = null,
        errorBlock: onFailed<Unit>? = null
    ) {
        val httpHeaders = HttpHeaders()
        headers?.takeIf { it.isNotEmpty() }?.forEach {
            httpHeaders.put(it.key, it.value)
        }

        val httpParams = HttpParams()
        params?.takeIf { it.isNotEmpty() }?.forEach {
            httpParams.put(it.key, it.value)
        }

        postFormData(url, httpHeaders, httpParams, block, errorBlock)
    }

    /**
     * 表单post
     */
    fun postFormData(
        url: String,
        httpHeaders: HttpHeaders?,
        httpParams: HttpParams,
        block: onSuccess<Unit>? = null,
        errorBlock: onFailed<Unit>? = null
    ) {
        postFiles(url, httpHeaders, httpParams, null, null, block, errorBlock)
    }

    fun postFiles(
        url: String,
        key: String,
        files: ArrayList<File>,
        block: onSuccess<Unit>? = null,
        errorBlock: onFailed<Unit>? = null
    ) {
        postFiles(url, null, null, key, files, block, errorBlock)
    }

    fun postFiles(
        url: String,
        params: Map<String, String>,
        key: String,
        files: List<File>,
        block: onSuccess<Unit>? = null,
        errorBlock: onFailed<Unit>? = null
    ) {
        postFiles(url, null, params, key, files, block, errorBlock)
    }

    fun postFiles(
        headers: Map<String, String>,
        url: String,
        key: String,
        files: List<File>,
        block: onSuccess<Unit>,
        errorBlock: onFailed<Unit>
    ) {
        postFiles(url, headers, null, key, files, block, errorBlock)
    }

    fun postFiles(
        url: String,
        headers: Map<String, String>?,
        params: Map<String, String>?,
        key: String,
        files: List<File>,
        block: onSuccess<Unit>? = null,
        errorBlock: onFailed<Unit>? = null
    ) {
        val httpHeaders = HttpHeaders()
        headers?.takeIf { it.isNotEmpty() }?.forEach {
            httpHeaders.put(it.key, it.value)
        }

        val httpParams = HttpParams()
        params?.takeIf { it.isNotEmpty() }?.forEach {
            httpParams.put(it.key, it.value)
        }

        postFiles(url, httpHeaders, httpParams, key, files, block, errorBlock)
    }

    /**
     * @Description 失败默认重复执行3次,总计4次,想要不重复执行调用 OkGo.retryCount(0)
     * @param url   上传地址
     * @param httpHeaders  请求头
     * @param httpParams   请求参数
     * @param key          键
     * @param files       文件集合
     * @param block     上传成功后调用的方法,参数为response.body()
     * @param errorBlock    上传失败后调用的方法
     */
    fun postFiles(
        url: String,
        httpHeaders: HttpHeaders?,
        httpParams: HttpParams,
        key: String?,
        files: List<File>?,
        block: onSuccess<Unit>? = null,
        errorBlock: onFailed<Unit>? = null
    ) {
        OkGo.post<String>(url)
            .tag(this)
            .headers(httpHeaders)
            .params(httpParams)
            .addFileParams(key, files)
            .isMultipart(true)
            .execute(object : StringCallback() {
                override fun onSuccess(response: Response<String>?) {
                    try {
                        response?.body()?.let {
                            block?.invoke(it)
                            return
                        }
                        errorBlock?.invoke("返回内容为空")
                    } catch (e: Exception) {
                        errorBlock?.invoke(e.stackTrace.toString())
                    }
                }

                override fun onError(response: Response<String>?) {
                    super.onError(response)
                    try {
                        response?.rawResponse?.message()?.let {
                            errorBlock?.invoke(it)
                            return
                        }
                        errorBlock?.invoke("网络错误")
                    } catch (e: Exception) {
                        errorBlock?.invoke(e.stackTrace.toString())
                    }
                }
            })
    }

    fun <T> postFiles(
        url: String,
        httpHeaders: HttpHeaders?,
        httpParams: HttpParams,
        key: String?,
        files: List<File>?,
        callback: Callback<T>
    ) {
        OkGo.post<T>(url)
            .tag(this)
            .headers(httpHeaders)
            .params(httpParams)
            .addFileParams(key, files)
            .isMultipart(true)
            .execute(callback)
    }

    fun postJson(
        url: String,
        block: onSuccess<Unit>? = null,
        errorBlock: onFailed<Unit>? = null
    ) {
        postJson(url, headers = null, null, block, errorBlock)
    }

    fun postJson(
        url: String,
        params: Map<String, Any>,
        block: onSuccess<Unit>? = null,
        errorBlock: onFailed<Unit>? = null
    ) {
        postJson(url, headers = null, params, block, errorBlock)
    }

    fun postJson(
        headers: Map<String, String>,
        url: String,
        block: onSuccess<Unit>,
        errorBlock: onFailed<Unit>
    ) {
        postJson(url, headers, null, block, errorBlock)
    }

    fun postJson(
        url: String,
        headers: Map<String, String>?,
        params: Map<String, Any>?,
        block: onSuccess<Unit>? = null,
        errorBlock: onFailed<Unit>? = null
    ) {
        val httpHeaders = HttpHeaders()
        headers?.takeIf { it.isNotEmpty() }?.forEach {
            httpHeaders.put(it.key, it.value)
        }

        postJson(url, httpHeaders, params, block, errorBlock)
    }

    fun postJson(
        url: String,
        httpHeaders: HttpHeaders?,
        params: Map<String, Any>?,
        block: onSuccess<Unit>? = null,
        errorBlock: onFailed<Unit>? = null
    ) {
        val json = JSONObject();
        params?.takeIf { it.isNotEmpty() }?.forEach {
            json.put(it.key, it.value)
        }
        val JSON = MediaType.parse("application/json; charset=utf-8");
        val body = RequestBody.create(JSON, json.toString());
        OkGo.post<String>(url)
            .tag(this)
            .headers(httpHeaders)
            .upRequestBody(body)
            .execute(object : StringCallback() {
                override fun onSuccess(response: Response<String>?) {
                    try {
                        response?.body()?.let {
                            block?.invoke(it)
                            return
                        }
                        errorBlock?.invoke("返回内容为空")
                    } catch (e: Exception) {
                        errorBlock?.invoke(e.stackTrace.toString())
                    }
                }

                override fun onError(response: Response<String>?) {
                    super.onError(response)
                    try {
                        response?.rawResponse?.message()?.let {
                            errorBlock?.invoke(it)
                            return
                        }
                        errorBlock?.invoke("网络错误")
                    } catch (e: Exception) {
                        errorBlock?.invoke(e.stackTrace.toString())
                    }
                }
            })
    }

    fun postJson(
        url: String,
        httpHeaders: HttpHeaders?,
        jsonString: String?,
        block: onSuccess<Unit>? = null,
        errorBlock: onFailed<Unit>? = null
    ) {

        val JSON = MediaType.parse("application/json; charset=utf-8");
        val body = RequestBody.create(JSON, jsonString);
        OkGo.post<String>(url)
            .tag(this)
            .headers(httpHeaders)
            .upRequestBody(body)
            .execute(object : StringCallback() {
                override fun onSuccess(response: Response<String>?) {
                    try {
                        response?.body()?.let {
                            block?.invoke(it)
                            return
                        }
                        errorBlock?.invoke("返回内容为空")
                    } catch (e: Exception) {
                        errorBlock?.invoke(e.stackTrace.toString())
                    }
                }

                override fun onError(response: Response<String>?) {
                    super.onError(response)
                    try {
                        response?.rawResponse?.message()?.let {
                            errorBlock?.invoke(it)
                            return
                        }
                        errorBlock?.invoke("网络错误")
                    } catch (e: Exception) {
                        errorBlock?.invoke(e.stackTrace.toString())
                    }
                }
            })
    }
    fun postJsonExecute(
        url: String,
        httpHeaders: HttpHeaders?,
        jsonString: String?,
        block: onSuccess<Unit>? = null,
        errorBlock: onFailed<Unit>? = null,
    ): String? {

        val JSON = MediaType.parse("application/json; charset=utf-8");
        try {
            val body = RequestBody.create(JSON, jsonString);
            return OkGo.post<String>(url)
                .tag(this)
                .headers(httpHeaders)
                .upRequestBody(body)
                .execute().body()?.string()
        }catch (e:java.lang.Exception){
            e.printStackTrace()
        }
        return ""
    }
}