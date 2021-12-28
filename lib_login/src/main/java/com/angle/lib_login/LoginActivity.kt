package com.angle.lib_login

import com.airbnb.lottie.LottieAnimationView
import com.alibaba.android.arouter.facade.annotation.Route
import com.angle.lib_common.base.BaseActivity
import com.angle.lib_router.LOGIN_LOGIN
import com.angle.lib_router.openHomePage
import com.gyf.immersionbar.ktx.immersionBar

@Route(path = LOGIN_LOGIN)
class LoginActivity : BaseActivity() {
    override fun configLayoutRes(): Int = R.layout.activity_login

    override fun initStatusBar() {
        immersionBar {
            statusBarDarkFont(true)
            statusBarView(findViewById(R.id.topTitle))
        }

        findViewById<LottieAnimationView>(R.id.loginLAV).setOnClickListener {
            openHomePage()
        }
    }
}