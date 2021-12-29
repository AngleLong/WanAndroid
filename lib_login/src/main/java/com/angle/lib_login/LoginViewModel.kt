package com.angle.lib_login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angle.lib_common.bean.BaseModel
import com.angle.lib_common.utils.loadDataState
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * 登陆的ViewModel
 */
class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginModel = BaseModel<LoginBean>()
    val loginModel = _loginModel

    fun login(userName: String, userPwd: String) {
//        viewModelScope.launch {
//            try {
//                val result = loginRepository.requestLogin(userName, userPwd)
//                Log.e("TAG", "login: $result")
//            } catch (e: Exception) {
//                Log.e("TAG", "login: 异常 ${e.toString()}")
//            }
//        }

        loadDataState(_loginModel, loader = { loginRepository.requestLogin(userName, userPwd) })
    }

    fun register(userName: String, userPwd: String, againPsd: String) {
        viewModelScope.launch {
            try {
                val result = loginRepository.requestRegister(userName, userPwd, againPsd)
                Log.e("TAG", "login: $result")
            } catch (e: Exception) {
                Log.e("TAG", "login: 异常 ${e.toString()}")
            }
        }
    }
}