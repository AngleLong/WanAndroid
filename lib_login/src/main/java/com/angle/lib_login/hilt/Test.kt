package com.angle.lib_login.hilt

import android.util.Log
import javax.inject.Inject

class Test @Inject constructor() {

    fun testMethod() {
        Log.e("TAG", "testMethod: 打印响应的数据")
    }
}