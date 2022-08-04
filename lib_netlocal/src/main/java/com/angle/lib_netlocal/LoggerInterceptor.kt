package com.angle.lib_netlocal

import okhttp3.*

class LoggerInterceptor : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        //1. 获取请求
        var request: Request = chain.request()

        //2. 处理自己的逻辑并创建一个新的request返回
        val newRequest: Request.Builder = request.newBuilder()

        //创建body
        val requestBody: RequestBody? = request.body

        //创建新的request
        request = newRequest.post(requestBody!!).build()

        /////////////上面是请求前,下面是请求后

        //3. 将新的request交由下个拦截器处理,并返回相应的response
        val response: Response = chain.proceed(request)

        // 注意，这样写，等于重新创建Request，获取新的Response，避免在执行以上代码时，
        // 调用了responseBody.string()而不能在返回体中再次调用。
        return response.newBuilder().build();
    }

}