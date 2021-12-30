package com.angle.lib_router

import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter

private const val HOME_GROUP = "home"

const val HOME_HOME = "/$HOME_GROUP/homeActivity"

fun openHomePage() {
    ARouter.getInstance()
        .build(HOME_HOME)
        .navigation()
}