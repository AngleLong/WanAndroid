package com.angle.lib_login

import androidx.lifecycle.ViewModel
import com.angle.lib_common.bean.BaseFlowModel
import com.angle.lib_common.bean.BaseModel
import com.angle.lib_common.utils.loadDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * 登陆的ViewModel
 */
@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository) :
    ViewModel() {

    private val _loginModel = BaseModel<LoginBean>()
    val loginModel = _loginModel

    private val _loginFlowData = BaseFlowModel<LoginBean>()
    val loginFlowData = _loginFlowData


    private val _register = BaseModel<RegisterBean>()
    val register = _register

    fun login(userName: String, userPwd: String) {
        //使用LiveData
        loadDataState(_loginModel, loader = { loginRepository.requestLogin(userName, userPwd) })

        //使用flow
//        loadDataStateFlow(_loginFlowData, loader = { loginRepository.requestLogin(userName, userPwd) })
    }

    fun register(userName: String, userPwd: String, againPsd: String) {
        loadDataState(_register,
            loader = { loginRepository.requestRegister(userName, userPwd, againPsd) })
    }
}