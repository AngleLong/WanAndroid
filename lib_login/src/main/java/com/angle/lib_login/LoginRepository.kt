package com.angle.lib_login

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginRepository @Inject constructor(private val loginApi: LoginApi) {

    suspend fun requestLogin(userName: String, userPwd: String): LoginBean =
        loginApi.login(userName, userPwd)


    fun requestLoginLiveData(userName: String, userPwd: String): LiveData<LoginBean> {
        return flow<LoginBean> { loginApi.login(userName, userPwd) }.asLiveData()

//        return liveData { loginApi.login(userName, userPwd) }
    }

    fun requestLoginFlow(userName: String, userPwd: String): Flow<LoginBean> {
        return flow {
            loginApi.login(userName, userPwd)
        }
    }

    suspend fun requestRegister(userName: String, userPwd: String, pwdAgain: String): RegisterBean =
        loginApi.register(userName, userPwd, pwdAgain)

}