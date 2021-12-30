package com.angle.wanandroid

import android.util.Log
import javax.inject.Inject

class Truck @Inject constructor() {
    fun deliver() {
        Log.e("TAG", "deliver: Truck is delivering cargo.")
    }
}