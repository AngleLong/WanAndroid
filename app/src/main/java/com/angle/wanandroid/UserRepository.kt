package com.angle.wanandroid

import javax.inject.Inject

class UserRepository @Inject constructor(private val userApi: UserApi) {

    suspend fun requestLogin(userName: String, userPwd: String): String =
        userApi.login(userName, userPwd)
}