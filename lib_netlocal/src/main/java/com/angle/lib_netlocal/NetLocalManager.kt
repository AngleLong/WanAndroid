package com.angle.lib_netlocal

import android.app.Application
import android.content.Context
import com.angle.lib_net.HiNet
import com.angle.lib_net.NetConfig
import okhttp3.logging.HttpLoggingInterceptor

/**
 * 本地网络请求的封装
 */
object NetLocalManager {

    private lateinit var application: Application

    fun init(context: Context) {
        application = context.applicationContext as Application

        //初始化相关的网络请求
        HiNet.init(
            NetConfig.Builder()
                .baseUrl("https://www.wanandroid.com/")
                .interceptors(arrayOf(TokenInterceptor(), HttpLoggingInterceptor(WanHttpLoggingInterceptor()).setLevel(HttpLoggingInterceptor.Level.BODY)))
                .converter(WanConverterFactory.create())
                .build()
        )
    }

    /**
     * 全局上下文
     */
    fun getAppContext(): Application {
        return application
    }
}