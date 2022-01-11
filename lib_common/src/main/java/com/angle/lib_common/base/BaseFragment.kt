package com.angle.lib_common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment


abstract class BaseFragment<DB : ViewDataBinding> : Fragment() {

    var dataBinding: DB? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        //创建相应的DB对象
        dataBinding = DataBindingUtil.inflate(inflater, configLayoutRes(), container, false)

        //设置状态栏
        initStatusBar()

        initView()

        initData()

        //规范相应的配置
        initConfig()

        return dataBinding?.root
    }

    /**
     * 设置相应的资源ID
     */
    @LayoutRes
    abstract fun configLayoutRes(): Int

    open fun initStatusBar() {
    }

    open fun initView() {

    }

    open fun initData() {

    }

    /**
     * 规范相应的配置
     */
    abstract fun initConfig()
}