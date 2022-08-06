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
    private var isFirstLoad = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        //创建相应的DB对象
        dataBinding = DataBindingUtil.inflate(inflater, configLayoutRes(), container, false)

        //设置状态栏
        initStatusBar()

        initView(dataBinding?.root, savedInstanceState)

        return dataBinding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isFirstLoad = true;
    }

    override fun onResume() {
        super.onResume()
        if (isFirstLoad) {
            initData()
            initEvent()
            isFirstLoad = false
        }
    }

    /**
     * 设置相应的资源ID
     */
    @LayoutRes
    abstract fun configLayoutRes(): Int

    open fun initStatusBar() {
    }

    open fun initView(rootView: View?, savedInstanceState: Bundle?) {

    }

    open fun initData() {

    }

    /**
     * 这个是进行相应的监听,我认为监听需要在这里重新开始.
     */
    abstract fun initEvent()
}