package com.angle.lib_net

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

/**
 * 负责提供OkHttp能提供的一下内容
 */
internal object OkHttpManager {

    /**
     * 通过netConfig创建okHttpclient
     */
    fun createOkHttpClient(netConfig: NetConfig): OkHttpClient {

        return OkHttpClient.Builder().apply {
            connectTimeout(netConfig.connectTimeout, TimeUnit.SECONDS)
            writeTimeout(netConfig.writeTimeout, TimeUnit.SECONDS)
            readTimeout(netConfig.readTimeout, TimeUnit.SECONDS)

            //添加拦截器,这里的拦截器是用户自己实现的,这样做的好处是在外部可以自由的处理
            netConfig.interceptors.forEach { interceptor ->
                addInterceptor(interceptor)
            }

            //todo 添加ssl的校验

            //todo Cookie的处理



        }.build()
    }


//    private fun getDefaultOkhttp(): OkHttpClient {
//        return OkHttpClient.Builder()
//            .connectTimeout(10, TimeUnit.SECONDS)
//            .writeTimeout(10, TimeUnit.SECONDS)
//            .readTimeout(10, TimeUnit.SECONDS)
//            .addInterceptor(HttpLoggingInterceptor(WanHttpLoggingInterceptor()).setLevel(RetrofitFactory.getLogLevel()))
//            .eventListenerFactory(PrintingEventListener.FACTORY)
//            .build()
//    }
}