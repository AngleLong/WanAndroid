package com.angle.lib_home.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.angle.lib_common.base.BaseFragment
import com.angle.lib_home.HomeController
import com.angle.lib_home.R
import com.angle.lib_home.databinding.FragmentHomeBinding
import com.angle.lib_login.LoginCallBack
import com.angle.lib_router.login
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


//https://juejin.cn/post/6997708252901277709
//https://blog.csdn.net/huangxiaoguo1/article/details/106618020
//https://www.imgeek.org/article/825359067
//https://github.com/Dboy233/FragmentNavigatorHideShow
//https://blog.csdn.net/weixin_42575043/article/details/108709467
@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val homeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    private val homeController: HomePagerListController by lazy {
        HomePagerListController{
            login(object : LoginCallBack {
                override fun loginSuccess() {
                    Log.e("TAG", "loginSuccess: ")
                }

                override fun loginError() {
                    Log.e("TAG", "loginError: ")
                }
            })
        }
    }

    override fun configLayoutRes(): Int = R.layout.fragment_home

    override fun initEvent() {
        homeViewModel.bannerModel.observe(this) { item ->
            Log.e("TAG", "initConfig: Banner响应请求")
            homeController.bannerList = item
        }
    }

    override fun initView(rootView: View?, savedInstanceState: Bundle?) {
        super.initView(rootView, savedInstanceState)
        dataBinding?.showRv?.setController(homeController)

//        //添加适配器
//        val mAdapter = HomeItemAdapter(requireContext())
//
//        dataBinding?.showRv?.let {
//            it.layoutManager = LinearLayoutManager(context)
//            it.adapter = mAdapter
//                .withLoadStateHeaderAndFooter(
//                    HeaderAdapter {
//                        mAdapter.refresh()
//                    },
//                    FooterAdapter {
//                        mAdapter.refresh()
//                    }
//                )
//        }

//        lifecycleScope.launch {
//            homeViewModel.requestListFlow().collect {
//                homeController.submitData(it)
//            }
//        }


        homeViewModel.requestListLivedata().observe(this@HomeFragment) {
            lifecycleScope.launch {
                homeController.submitData(it)
            }
        }

    }

    override fun initData() {
        super.initData()

//        homeViewModel.requestBannerFlow().observe(this) {
//            Log.e("TAG", "initConfig1: Banner响应请求")
//        }
        homeViewModel.requestBannerFlow()
//        homeViewModel.requestList()
    }
}