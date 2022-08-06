package com.angle.lib_common.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angle.lib_common.base.LoadingStatus
import com.angle.lib_common.bean.BaseFlowModel
import com.angle.lib_common.bean.BaseModel
import com.angle.lib_common.bean.ErrorModel
import com.angle.lib_netlocal.ApiException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

fun <T> ViewModel.loadDataState(
    baseModel: BaseModel<T>,
    loader: suspend (ioScope: CoroutineScope) -> T,
) {
    viewModelScope.launch {
        //对话框开始加载
        baseModel.loadingStatus.postValue(LoadingStatus.START)
        try {
            val result = loader(this)
            baseModel.data.postValue(result)
            baseModel.loadingStatus.postValue(LoadingStatus.FINISH)
        } catch (e: Exception) {
            if (e is ApiException) {
                // TODO 其实这里可以处理一些异常逻辑 比如返回多少直接处理响应的异常
                baseModel.error.postValue(ErrorModel(e.errorCode, e.errorMsg))
            } else {
                baseModel.error.postValue(ErrorModel(-10000, e.toString()))
            }
            baseModel.loadingStatus.postValue(LoadingStatus.FINISH)
        }
    }
}

fun <T> ViewModel.loadDataStateFlow(
    baseModel: BaseFlowModel<T>,
    loader: suspend (ioScope: CoroutineScope) -> T
) {
    viewModelScope.launch {
        flow<BaseFlowModel<T>> {

            baseModel.loadingStatus.emit(LoadingStatus.START)

            try {
                val result = loader(this@launch)
                baseModel.data.emit(result)
                baseModel.loadingStatus.emit(LoadingStatus.FINISH)
            } catch (e: Exception) {
                if (e is ApiException) {
                    // TODO 其实这里可以处理一些异常逻辑 比如返回多少直接处理响应的异常
                    baseModel.error.emit(ErrorModel(e.errorCode, e.errorMsg))
                } else {
                    baseModel.error.emit(ErrorModel(-10000, e.toString()))
                }
                baseModel.loadingStatus.emit(LoadingStatus.FINISH)
            }

        }
    }
}