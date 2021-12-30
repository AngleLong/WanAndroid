package com.angle.lib_login

import com.angle.lib_net.RetrofitFactory
import javax.inject.Inject

class LoginRepository @Inject constructor(private val loginApi: LoginApi) {

//    private val loginApi = RetrofitFactory.getDefaultService(LoginConfigUtils.baseUrl,
//        clazz = LoginApi::class.java)

    suspend fun requestLogin(userName: String, userPwd: String): LoginBean =
        loginApi.login(userName, userPwd)

    suspend fun requestRegister(userName: String, userPwd: String, pwdAgain: String): RegisterBean =
        loginApi.register(userName, userPwd, pwdAgain)

}