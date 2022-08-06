package com.angle.lib_netlocal

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.Interceptor
import okhttp3.Response

/**
 * 这个是为了演示token过期的时候进行请求更新token
 */
class TokenInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        //拿到请求
        val request = chain.request()

        //获取响应
        var response = chain.proceed(request)

        Log.d("TAG", "获取的响应码= ${response.code}")

        if (isTokenExpired(response)) {
            //进行网络请求,这里直接用runBlocking就可以了
            runBlocking(Dispatchers.Main) {
                val token = withContext(Dispatchers.IO) {
                    //这里拿到token直接保存,然后再下面在取出来,因为runBlock相当于是同步的.所以这里不会有问题
                    return@withContext "2301021"
                }

                //这里需要一个全局的上下文,所以这里你要么用全局的,这里的上下文不应该封装到框架里面,所以这个只能拿到外面去写
                SPUtils.put(NetLocalManager.getAppContext(), "authorization_token", token)
            }

            //这里直接使用sp从本地存储中拿就好了
            val token = SPUtils[NetLocalManager.getAppContext(), "authorization_token", ""] as String
            Log.d("TAG", "获取的token= $token")
            val newRequest = chain.request().newBuilder().header("Authorization", token).build()
            response.close()
            response = chain.proceed(newRequest)
            return response
        }

        //正常返回结果
        return response

    }

    /**
     * 根据响应体来判断token是否过期
     * 这里默认是走这个请求拦截器
     */
    fun isTokenExpired(response: Response): Boolean {
        return true
    }

}