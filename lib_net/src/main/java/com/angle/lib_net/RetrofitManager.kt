package com.angle.lib_net

import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * 负责提供Retrofit需要提供的一些方法
 */
internal object RetrofitManager {
    /**
     * 创建相应的Service
     */
    @JvmStatic
    fun <T> createService(
        netConfig: NetConfig,
        client: OkHttpClient,
        serviceClass: Class<T>,
    ): T {
        return createRetrofit(netConfig, client)
            .create(serviceClass)
    }

    /**
     * 创建Retrofit实例
     */
    @JvmStatic
    private fun createRetrofit(
        netConfig: NetConfig,
        client: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder().apply {
            baseUrl(netConfig.baseUrl)
            client(client)
            //添加转换器,这样就能把获取到的网络数据转换成需要的类型,默认是Gson的转换
            addConverterFactory(netConfig.converter)
        }.build()

    }
}