package com.angle.lib_home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.lottie.LottieAnimationView
import com.alibaba.android.arouter.facade.annotation.Route
import com.angle.lib_common.base.BaseActivity
import com.angle.lib_home.databinding.ActivityHomeBinding
import com.angle.lib_router.HOME_HOME
import com.angle.lib_router.openHomePage
import com.gyf.immersionbar.ktx.immersionBar

@Route(path = HOME_HOME)
class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    override fun configLayoutRes(): Int = R.layout.activity_home

    override fun initStatusBar() {

        immersionBar {
            statusBarDarkFont(true)
            statusBarView(findViewById(R.id.topTitle))
        }
    }

    override fun initConfig() {
        TODO("Not yet implemented")
    }
}