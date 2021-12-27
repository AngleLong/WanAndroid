package com.angle.wanandroid

class UserRepository(private val userApi: UserApi) {

    suspend fun requestLogin(userName: String, userPwd: String): String =
        userApi.login(userName, userPwd)
}