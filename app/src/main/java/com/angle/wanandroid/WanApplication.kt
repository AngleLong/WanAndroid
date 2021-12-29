package com.angle.wanandroid

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.angle.lib_common.AppConfig
import com.angle.lib_common.base.BaseApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WanApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()

        initModuleApp(this)
        initModuleData(this)

        initArouter()
    }

    private fun initArouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }

        ARouter.init(this);
    }

    override fun initModuleApp(application: Application) {
        for (item in AppConfig.moduleApps) {
            val clazz = Class.forName(item)
            val baseApp = clazz.newInstance() as BaseApplication
            baseApp.initModuleApp(application)
        }
    }

    override fun initModuleData(application: Application) {
        for (item in AppConfig.moduleApps) {
            val clazz = Class.forName(item)
            val baseApp = clazz.newInstance() as BaseApplication
            baseApp.initModuleData(application)
        }
    }
}