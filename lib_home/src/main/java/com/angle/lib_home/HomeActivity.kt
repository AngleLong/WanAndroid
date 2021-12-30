package com.angle.lib_home

import com.alibaba.android.arouter.facade.annotation.Route
import com.angle.lib_common.base.WanBaseActivity
import com.angle.lib_home.databinding.ActivityHomeBinding
import com.angle.lib_router.HOME_HOME
import com.gyf.immersionbar.ktx.immersionBar

@Route(path = HOME_HOME)
class HomeActivity : WanBaseActivity<ActivityHomeBinding>() {

    override fun configLayoutRes(): Int = R.layout.activity_home

    override fun initStatusBar() {

        immersionBar {
            statusBarDarkFont(true)
//            statusBarView(findViewById(R.id.topTitle))
        }
    }

    override fun initConfig() {
    }

    override fun isEnableSwipe(): Boolean {
        return false
    }
}