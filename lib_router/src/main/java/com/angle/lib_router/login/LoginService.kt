package com.angle.lib_router.login

import com.alibaba.android.arouter.facade.template.IProvider

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