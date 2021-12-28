package com.angle.lib_common.base

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper
import com.angle.lib_common.AdapterScreenUtils
import com.angle.lib_common.R
import com.gyf.immersionbar.ktx.immersionBar

/**
 * 所有Activity的基类
 */
abstract class BaseActivity : AppCompatActivity(), BGASwipeBackHelper.Delegate {

    //    private val mSwipeBackHelper by lazy {
//        Log.e("TAG", ":1 " )
//        BGASwipeBackHelper(this, this).apply {
//            //侧滑返回是否可用
//            setSwipeBackEnable(isEnableSwipe())
//
//            //仅支持左侧返回
//            setIsOnlyTrackingLeftEdge(true)
//        }
//    }
    private var mSwipeBackHelper: BGASwipeBackHelper? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        initSwipeBackFinish()

        Log.e("TAG", ":2 ")
        super.onCreate(savedInstanceState)

        setContentView(configLayoutRes())

        //设置状态栏
        initStatusBar()
    }

    private fun initSwipeBackFinish() {
        Log.e("TAG", ":1 ")
        mSwipeBackHelper = BGASwipeBackHelper(this, this).apply {
            Log.e("TAG", ":3 ${isEnableSwipe()}")
            setSwipeBackEnable(isEnableSwipe())
            setIsOnlyTrackingLeftEdge(true)
//            // 设置滑动返回是否可用。默认值为 true
//            setSwipeBackEnable(true);
//            // 设置是否仅仅跟踪左侧边缘的滑动返回。默认值为 true
//            setIsOnlyTrackingLeftEdge(true);
//            // 设置是否是微信滑动返回样式。默认值为 true
            setIsWeChatStyle(false);
//            // 设置阴影资源 id。默认值为 R.drawable.bga_sbl_shadow
//            setShadowResId(R.drawable.bga_sbl_shadow);
//            // 设置是否显示滑动返回的阴影效果。默认值为 true
//            setIsNeedShowShadow(true);
//            // 设置阴影区域的透明度是否根据滑动的距离渐变。默认值为 true
//            setIsShadowAlphaGradient(true);
//            // 设置触发释放后自动滑动返回的阈值，默认值为 0.3f
//            setSwipeBackThreshold(0.3f);
//            // 设置底部导航条是否悬浮在内容上，默认值为 false
//            setIsNavigationBarOverlap(false);
        }
    }

    open fun initStatusBar() {
//        immersionBar {
//            statusBarColor(R.color.purple_200)
//            navigationBarColor(R.color.purple_200)
//        }
    }

    /**
     * 设置相应的资源ID
     */
    @LayoutRes
    abstract fun configLayoutRes(): Int

    open fun isEnableSwipe(): Boolean {
        return true
    }

    override fun getResources(): Resources {
        return AdapterScreenUtils.adaptWidth(super.getResources(), 750)
    }

    override fun isSupportSwipeBack(): Boolean {
        return isEnableSwipe()
    }

    override fun onSwipeBackLayoutSlide(slideOffset: Float) {
        Log.e("TAG", "onSwipeBackLayoutSlide: $slideOffset")
    }

    override fun onSwipeBackLayoutCancel() {
    }

    override fun onSwipeBackLayoutExecuted() {
        mSwipeBackHelper?.swipeBackward();
    }
}