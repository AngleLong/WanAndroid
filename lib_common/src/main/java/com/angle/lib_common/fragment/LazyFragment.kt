package com.angle.lib_common.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

/**
 * 实现懒加载的Fragment
 */
abstract class LazyFragment : Fragment() {

    private var isFirstLoad = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = LayoutInflater.from(context).inflate(getContentViewId(), null)
        initView(rootView, savedInstanceState)
        return rootView
    }


    override fun onDestroyView() {
        super.onDestroyView()
        isFirstLoad = true;
    }

    override fun onResume() {
        super.onResume()
        if (isFirstLoad) {
            initData();
            initEvent();
            isFirstLoad = false
        }
    }

    abstract fun initView(rootView: View, savedInstanceState: Bundle?)

    abstract fun getContentViewId(): Int

    abstract fun initData()

    abstract fun initEvent()
}