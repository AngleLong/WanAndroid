package com.angle.lib_router

import android.content.Context
import android.util.Log
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Interceptor
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.alibaba.android.arouter.facade.template.IInterceptor

@Interceptor(priority = 8, name = "登陆")
class LoginInterceptor : IInterceptor {
    override fun init(context: Context?) {
        Log.e("TAG", "process: priority = 8")
    }

    override fun process(postcard: Postcard?, callback: InterceptorCallback?) {
        val path = postcard?.path
        Log.e("TAG", "process: $path")
        if (false) {
            //已经登陆
            //不拦截直接过
            callback?.onContinue(postcard)
        } else {
            openLoginPage()
//            //没有登陆 写了该方法之后就会拦截到相应的跳转
//            callback?.onInterrupt(null)
        }
    }
}

class LoginNavigationCallbackImpl : NavigationCallback {
    override fun onFound(postcard: Postcard?) {
        //找到了
        Log.e("TAG", "onFound: 找到了")
    }

    override fun onLost(postcard: Postcard?) {
        //找不到了
        Log.e("TAG", "onLost: 找不到了")
    }

    override fun onArrival(postcard: Postcard?) {
        //跳转成功了
        Log.e("TAG", "onArrival: 跳转成功了")
    }

    override fun onInterrupt(postcard: Postcard?) {
        //拦截了
        Log.e("TAG", "onInterrupt: 拦截了")

    }

}
