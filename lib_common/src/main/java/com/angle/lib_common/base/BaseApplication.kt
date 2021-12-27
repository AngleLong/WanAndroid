package com.angle.lib_common.base

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper

/**
 * 基类中的Application
 */
abstract class BaseApplication : Application(){

    companion object {
        @SuppressLint("StaticFieldLeak")
        var context: Context? = null

        fun getInstance(): Context {
            return context!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = this

        BGASwipeBackHelper.init(this, null)
    }

    /**
     * 初始化相应的App
     */
    abstract fun initModuleApp(application: Application)

    /**
     * 自定义的操作
     */
    abstract fun initModuleData(application: Application)
}