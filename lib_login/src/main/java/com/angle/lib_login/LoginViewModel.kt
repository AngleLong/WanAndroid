package com.angle.lib_login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angle.lib_common.bean.BaseModel
import com.angle.lib_common.utils.BaseFlowModel
import com.angle.lib_common.utils.loadDataState
import com.angle.lib_common.utils.loadDataStateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.lang.Exception
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