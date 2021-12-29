package com.angle.lib_common.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angle.lib_common.bean.BaseModel
import com.angle.lib_common.bean.ErrorModel
import com.angle.lib_net.ApiException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun <T> ViewModel.loadDataState(
    baseModel: BaseModel<T>,
    loader: suspend (ioScope: CoroutineScope) -> T,
) {
    viewModelScope.launch {
        try {
            val result = loader(this)
            baseModel.data.postValue(result)
        } catch (e: Exception) {
            if (e is ApiException) {
                // TODO 其实这里可以处理一些异常逻辑 比如返回多少直接处理响应的异常
                baseModel.error.postValue(ErrorModel(e.errorCode, e.errorMsg))
            } else {
                baseModel.error.postValue(ErrorModel(-10000, e.toString()))
            }
        }
    }
}