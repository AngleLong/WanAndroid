package com.angle.lib_common.bean

import androidx.lifecycle.MutableLiveData
import com.angle.lib_common.base.LoadingStatus

/**
 * 这个类主要是为了封装成功和失败的模型
 */
data class BaseModel<T>(
    val data: MutableLiveData<T?> = MutableLiveData(),
    val error: MutableLiveData<ErrorModel> = MutableLiveData(),
    val loadingStatus: MutableLiveData<LoadingStatus> = MutableLiveData(),
)