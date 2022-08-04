package com.angle.lib_login

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.angle.lib_common.utils.SPUtils
import com.angle.lib_router.LOGIN_SERVICE
import com.angle.lib_router.login.LoginService
import com.angle.lib_router.openLoginPage

@Route(path = LOGIN_SERVICE, name = "登陆服务")
class LoginServiceImpl : LoginService {

    private var context: Context? = null

    override fun init(context: Context?) {
        this.context = context
    }

    override fun login(loginCallBack: LoginCallBack) {
        if (isLogin()) {
            loginCallBack.loginSuccess()
        } else {
            //设置好监听
            LoginCallbackOpt.setLoginCallBack(loginCallBack)
            openLoginPage()
        }
    }

    override fun isLogin(): Boolean {
        if (context == null) return false
        return SPUtils[context!!, "isLogin", false] as Boolean
    }
}