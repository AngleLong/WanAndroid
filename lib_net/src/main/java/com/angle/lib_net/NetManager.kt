package com.angle.lib_net

import android.text.TextUtils
import okhttp3.OkHttpClient
import java.lang.RuntimeException

/**
 * 整个网络请求的管理类
 * 这里看看使用单例和非单例对后面的影响有多大
 * 1. 创建Service
 * 2.
 */
internal object NetManager {
    /**
     * 创建相应的api
     * 这里传递baseUrl的主要目的是指定相应的Api可以使用其他接口进行网络请求
     * 这里其实对用户来说不应该知道OkHttpClient是怎么创建的
     */
    fun <T> createRetrofitApi(
        netConfig: NetConfig,
        serviceClass: Class<T>
    ): T {

        val okHttpClient = OkHttpManager.createOkHttpClient(netConfig)

        return RetrofitManager.createService(netConfig, okHttpClient, serviceClass)
    }
}