package com.angle.lib_common.base

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper
import com.angle.lib_common.AdapterScreenUtils
import com.angle.lib_common.R
import com.gyf.immersionbar.ktx.immersionBar
import android.widget.EditText

import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager


/**
 * 所有Activity的基类
 */
abstract class BaseActivity<DB : ViewDataBinding> : AppCompatActivity(),
    BGASwipeBackHelper.Delegate {

    private var mSwipeBackHelper: BGASwipeBackHelper? = null

    var dataBinding: DB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        //增加侧滑返回
        initSwipeBackFinish()
        super.onCreate(savedInstanceState)

        //设置布局
        dataBinding = DataBindingUtil.setContentView(this, configLayoutRes())

        //设置状态栏
        initStatusBar()

        //规范相应的配置
        initConfig()
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


    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            val v: View? = currentFocus
            if (isShouldHideInput(v, ev)) {
                val imm: InputMethodManager =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                if (v != null) {
                    imm.hideSoftInputFromWindow(v.windowToken, 0)
                }
            }
            return super.dispatchTouchEvent(ev)
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        return if (window.superDispatchTouchEvent(ev)) {
            true
        } else onTouchEvent(ev)
    }

    /**
     * 是否显示键盘
     *
     * @param v
     * @param event
     * @return
     */
    open fun isShouldHideInput(v: View?, event: MotionEvent): Boolean {
        if (v != null && v is EditText) {
            val leftTop = intArrayOf(0, 0)
            // 获取输入框当前的location位置
            v.getLocationInWindow(leftTop)
            val left = leftTop[0]
            val top = leftTop[1]
            val bottom: Int = top + v.getHeight()
            val right: Int = left + v.getWidth()
            if (event.x > right && event.y > top && event.y < bottom) {
                //如果是输入框右边的部分就保留
                return false
            }
            return !(event.x > left && event.x < right && event.y > top && event.y < bottom)
        }
        return false
    }

    /**
     * 设置相应的资源ID
     */
    @LayoutRes
    abstract fun configLayoutRes(): Int

    /**
     * 规范相应的配置
     */
    abstract fun initConfig()

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