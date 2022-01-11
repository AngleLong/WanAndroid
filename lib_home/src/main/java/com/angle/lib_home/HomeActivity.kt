package com.angle.lib_home

import android.view.View
import androidx.core.view.forEach
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.alibaba.android.arouter.facade.annotation.Route
import com.angle.lib_common.base.WanBaseActivity
import com.angle.lib_home.databinding.ActivityHomeBinding
import com.angle.lib_router.HOME_HOME
import com.gyf.immersionbar.ktx.immersionBar
import dagger.hilt.android.AndroidEntryPoint

@Route(path = HOME_HOME)
@AndroidEntryPoint
class HomeActivity : WanBaseActivity<ActivityHomeBinding>() {

    override fun configLayoutRes(): Int = R.layout.activity_home

    override fun initStatusBar() {

        immersionBar {
            statusBarDarkFont(true)
        }
    }

    override fun initView() {

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_bottom) as NavHostFragment
        val navController = navHostFragment.navController

        NavigationUI.setupWithNavController(dataBinding!!.navView, navController)

        //去掉BottomNavigationView长按弹出Toast(https://stackoverflow.com/questions/57688720/disable-tooltiptext-in-bottomnavigationview)
        dataBinding?.navView?.menu?.forEach {
            val view =  dataBinding?.navView?.findViewById<View>(it.itemId)
            view?.setOnLongClickListener {
                true
            }
        }
    }

    override fun initConfig() {

    }

    override fun isEnableSwipe(): Boolean {
        return false
    }
}