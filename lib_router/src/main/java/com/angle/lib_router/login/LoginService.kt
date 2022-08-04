package com.angle.lib_router.login

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.template.IProvider
import com.angle.lib_login.LoginCallBack

/**
 * 登陆提供的服务
 */
interface LoginService : IProvider {

    /**
     * 登陆的方法
     */
    fun login(loginCallBack: LoginCallBack)

    /**
     * 是否登陆
     */
    fun isLogin(): Boolean
}