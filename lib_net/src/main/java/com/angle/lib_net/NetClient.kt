package com.angle.lib_net

import android.text.TextUtils
import java.lang.RuntimeException

object HiNet {

    /**
     * 网络请求配置
     */
    private lateinit var mNetConfig: NetConfig

    /**
     * 是否初始化的标识
     */
    private var isInit: Boolean = false

    /**
     * 初始化的方法
     */
    fun init(baseUrl: String) {
        this.init(netConfig = NetConfig.defaultNetConfig(baseUrl))
    }

    fun init(netConfig: NetConfig) {
        if (TextUtils.isEmpty(netConfig.baseUrl)) {
            throw RuntimeException("请使用netConfig配置正确的baseUrl")
        }

        isInit = true

        mNetConfig = netConfig
    }

    fun <T> createRetrofitApi(
        serviceClass: Class<T>,
        baseUrl: String = mNetConfig.baseUrl
    ): T {
        if (!isInit) throw  RuntimeException("没有调用NetManager的init()初始化方法")
        mNetConfig.baseUrl = baseUrl
        return NetManager.createRetrofitApi(mNetConfig, serviceClass)
    }
}