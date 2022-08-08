package com.angle.lib_router.login

import java.lang.ref.SoftReference

//登陆的扩展方法

object LoginCallbackOpt {
//    //这里通过软引用来进行保存
//    private var loginCallBack: LoginCallBack? = null
    private var softReferenceLoginCallback: SoftReference<LoginCallBack>? = null

    fun setLoginCallBack(loginCallBack: LoginCallBack) {
        softReferenceLoginCallback = SoftReference(loginCallBack)
    }

    fun getLoginCallBack(): LoginCallBack? {
        return softReferenceLoginCallback?.get()
    }
}

interface LoginCallBack {
    fun loginSuccess()
    fun loginError()
}