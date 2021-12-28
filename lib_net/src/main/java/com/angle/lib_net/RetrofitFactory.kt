package com.angle.lib_net

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * 创建Retrofit的工厂
 */
object RetrofitFactory {

    /**
     * 直接创建一个Service
     */
    fun <T> getDefaultService(
        baseUrl: String,
        okHttpClient: OkHttpClient = getDefaultOkhttp(),
        clazz: Class<T>,
    ): T = RetrofitCreator.createService(baseUrl, okHttpClient, clazz)

    /**
     * 获取日志登记
     */
    private fun getLogLevel(): HttpLoggingInterceptor.Level {
        return if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.BASIC
        }
    }

    /**
     * 一般在项目重创建的OkHttpClient都是一样的.
     * todo 这里先写成这样,之后有需求在加
     */
    private fun getDefaultOkhttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor(WanHttpLoggingInterceptor()).setLevel(getLogLevel()))
            .build()
    }
}