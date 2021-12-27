package com.angle.lib_common

object AppConfig {
    /**
     * homeApp的Application
     */
    private const val homeApp = "com.angle.lib_home.HomeApp"

    /**
     * 登陆页面的Application
     */
    private const val loginApp = "com.angle.lib_login.LoginApp"

    val moduleApps = arrayOf(homeApp, loginApp)
}