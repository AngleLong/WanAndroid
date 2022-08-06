package com.angle.lib_netlocal

import android.util.Log
import okhttp3.logging.HttpLoggingInterceptor

class WanHttpLoggingInterceptor :HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        Log.e("wanHttpLog->", "log: $message" )
    }
}