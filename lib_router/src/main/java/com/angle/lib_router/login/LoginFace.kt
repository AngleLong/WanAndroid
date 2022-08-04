package com.angle.lib_router

import com.alibaba.android.arouter.launcher.ARouter
import com.angle.lib_login.LoginCallBack
import com.angle.lib_router.login.LoginService

private const val Login_GROUP = "login"

const val LOGIN_LOGIN = "/$Login_GROUP/loginActivity"
const val LOGIN_SERVICE = "/$Login_GROUP/loginService"

fun openLoginPage() {
    ARouter.getInstance()
        .build(LOGIN_LOGIN)
        .navigation()
}

fun login(loginCallBack: LoginCallBack) {
    ARouter.getInstance().navigation(LoginService::class.java).login(loginCallBack)
}