package com.angle.lib_login

import android.app.Application
import android.util.Log
import com.angle.lib_common.base.BaseApplication

class LoginApp : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        initModuleApp(this)
        initModuleData(this)
    }

    override fun initModuleApp(application: Application) {
        Log.e("TAG", "LoginApp->initModuleApp: ")
    }

    override fun initModuleData(application: Application) {
        Log.e("TAG", "LoginApp->initModuleData: ")
    }
}