package com.angle.lib_home

import android.app.Application
import android.util.Log
import com.angle.lib_common.base.BaseApplication

/**
 * home模块的Application
 */
class HomeApp : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        initModuleApp(this)
        initModuleData(this)
    }

    override fun initModuleApp(application: Application) {
        Log.e("TAG", "HomeApp->initModuleApp: ")
    }

    override fun initModuleData(application: Application) {
        Log.e("TAG", "HomeApp->initModuleData: ")
    }
}