package com.angle.lib_net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * retrofit创建
 */
object RetrofitCreator {

    /**
     * 创建相应的Service
     */
    @JvmStatic
    fun <T> createService(
        baseUrl: String,
        client: OkHttpClient,
        serviceClass: Class<T>,
    ): T {
        return createRetrofit(baseUrl, client)
            .create(serviceClass)
    }


    @JvmStatic
    fun createRetrofit(
        baseUrl: String,
        client: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}