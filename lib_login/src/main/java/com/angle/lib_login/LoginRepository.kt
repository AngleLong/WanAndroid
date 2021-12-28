package com.angle.lib_login

class LoginRepository(private val loginApi: LoginApi) {

    suspend fun requestLogin(userName: String, userPwd: String): LoginBean =
        loginApi.login(userName, userPwd)

    suspend fun requestRegister(userName: String, userPwd: String, pwdAgain: String): RegisterBean =
        loginApi.register(userName, userPwd, pwdAgain)

}