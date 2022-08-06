package com.angle.lib_front

import android.annotation.SuppressLint
import android.util.Log
import androidx.arch.core.util.Function
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.lifecycleScope
import com.angle.lib_common.base.BaseActivity
import com.angle.lib_front.databinding.ActivitySplashBinding
import com.angle.lib_router.openHomePage
import com.angle.lib_router.openLoginPage
import com.gyf.immersionbar.ktx.immersionBar
import kotlinx.coroutines.*


@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun configLayoutRes(): Int = R.layout.activity_splash

    override fun initStatusBar() {
        immersionBar {
            statusBarDarkFont(true)
        }
    }

    override fun initConfig() {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                delay(3000)
                openHomePage()
                finish()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycleScope.cancel()
    }
}