package com.angle.lib_net

import okhttp3.Interceptor
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory

/**
 * 网络请求配置类,主要是提供一些相关的配置
 */
class NetConfig constructor(private val builder: Builder) {

    companion object {
        fun defaultNetConfig(baseUrlTran: String): NetConfig {
            return Builder()
                .baseUrl(baseUrlTran)
                .build()
        }
    }

    /**
     * 基础的网络路径
     */
    internal var baseUrl: String = builder.baseUrl

    /**
     * 链接时长
     */
    internal var connectTimeout: Long = builder.connectTimeout

    /**
     * 写入时长
     */
    internal var writeTimeout: Long = builder.writeTimeout

    /**
     * 读取时长
     */
    internal var readTimeout: Long = builder.readTimeout

    /**
     * 拦截器的数组
     */
    internal var interceptors: Array<Interceptor> = builder.interceptors

    /**
     * 网络请求转换器
     */
    internal var converter: Converter.Factory = builder.converter


    class Builder constructor() {
        internal var baseUrl: String = ""
        internal var connectTimeout: Long = 10
        internal var writeTimeout: Long = 10
        internal var readTimeout: Long = 10
        internal var interceptors: Array<Interceptor> = arrayOf()
        internal var converter: Converter.Factory = GsonConverterFactory.create()

        /**
         * 这是一个提供相应拷贝的方法
         */
        constructor(netConfig: NetConfig) : this() {
            this.baseUrl = netConfig.baseUrl
            this.connectTimeout = netConfig.connectTimeout
            this.writeTimeout = netConfig.writeTimeout
            this.readTimeout = netConfig.readTimeout
            this.interceptors = netConfig.interceptors
            this.converter = netConfig.converter
        }

        fun baseUrl(baseUrl: String) = apply {
            this.baseUrl = baseUrl
        }

        fun connectTimeout(connectTimeout: Long) = apply {
            this.connectTimeout = connectTimeout
        }

        fun writeTimeout(writeTimeout: Long) = apply {
            this.writeTimeout = writeTimeout
        }

        fun readTimeout(readTimeout: Long) = apply {
            this.readTimeout = readTimeout
        }

        fun interceptors(interceptors: Array<Interceptor>) = apply {
            this.interceptors = interceptors
        }

        fun converter(converter: Converter.Factory) = apply {
            this.converter = converter
        }

        fun build(): NetConfig = NetConfig(this)
    }

}