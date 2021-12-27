package com.angle.lib_router

import com.alibaba.android.arouter.launcher.ARouter

private const val Login_GROUP = "login"

const val LOGIN_LOGIN = "/$Login_GROUP/loginActivity"

fun openLoginPage() {
    ARouter.getInstance()
        .build(LOGIN_LOGIN)
        .navigation()
}