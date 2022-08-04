package com.angle.lib_home

import android.view.View
import androidx.core.view.forEach
import androidx.core.view.get
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.facade.annotation.Route
import com.angle.lib_common.base.WanBaseActivity
import com.angle.lib_home.databinding.ActivityHomeBinding
import com.angle.lib_home.ui.dashboard.DashboardFragment
import com.angle.lib_home.ui.home.HomeFragment
import com.angle.lib_home.ui.notifications.NotificationsFragment
import com.angle.lib_router.HOME_HOME
import com.google.android.material.navigation.NavigationBarView
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

//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_bottom) as NavHostFragment
//        val navController = navHostFragment.navController
//
//        NavigationUI.setupWithNavController(dataBinding!!.navView, navController)

        val fragments = mutableListOf(HomeFragment(), DashboardFragment(), NotificationsFragment(), DashboardFragment())
        dataBinding?.viewPager2?.apply {
            isUserInputEnabled = false
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                    dataBinding?.navView?.menu?.getItem(position)?.isChecked = true
                }
            })
        }?.adapter = ViewPager2Adapter(this, fragments)

        dataBinding?.navView?.apply {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.navigation_home -> {
                        dataBinding?.viewPager2?.currentItem = 0
                    }
                    R.id.navigation_dashboard -> {
                        dataBinding?.viewPager2?.currentItem = 1
                    }
                    R.id.navigation_notifications -> {
                        dataBinding?.viewPager2?.currentItem = 2
                    }
                    R.id.navigation_my -> {
                        dataBinding?.viewPager2?.currentItem = 3
                    }
                }

                true
            }
            //超过4个图标显示不同的处理
            labelVisibilityMode = NavigationBarView.LABEL_VISIBILITY_LABELED
        }?.menu?.forEach {
            //去掉BottomNavigationView长按弹出Toast(https://stackoverflow.com/questions/57688720/disable-tooltiptext-in-bottomnavigationview)
            val view = dataBinding?.navView?.findViewById<View>(it.itemId)
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