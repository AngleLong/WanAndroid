package com.angle.lib_router

import com.alibaba.android.arouter.launcher.ARouter

private const val SPLASH_GROUP = "splash"

const val SPLASH_Home = "/$SPLASH_GROUP/splashActivity"

fun openSplashPage() {
    ARouter.getInstance()
        .build(SPLASH_Home)
        .navigation()
}