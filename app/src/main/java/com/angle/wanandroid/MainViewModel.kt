package com.angle.wanandroid

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    fun login(userName: String, userPwd: String) {
        viewModelScope.launch {
            try {
                val result = userRepository.requestLogin(userName, userPwd)
                Log.e("TAG", "login: $result")
            } catch (e: Exception) {
                Log.e("TAG", "login: 异常 ${e.toString()}")
            }
        }
    }

    fun register(userName: String, userPwd: String) {
        viewModelScope.launch {
            try {
                val result = userRepository.requestLogin(userName, userPwd)
                Log.e("TAG", "login: $result")
            } catch (e: Exception) {
                Log.e("TAG", "login: 异常 ${e.toString()}")
            }
        }
    }
}