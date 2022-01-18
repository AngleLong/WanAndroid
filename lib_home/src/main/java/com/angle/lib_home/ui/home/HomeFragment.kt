package com.angle.lib_home.ui.home

import android.util.Log
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.angle.lib_common.base.BaseFragment
import com.angle.lib_home.HomeController
import com.angle.lib_home.R
import com.angle.lib_home.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


//https://juejin.cn/post/6997708252901277709
//https://blog.csdn.net/huangxiaoguo1/article/details/106618020
@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val homeViewModel by viewModels<HomeViewModel>()

    @Inject
    lateinit var homeController: HomePagerListController

    override fun configLayoutRes(): Int = R.layout.fragment_home

    override fun initConfig() {
        homeViewModel.bannerModel.observe(this) { item ->
            Log.e("TAG", "initConfig: Banner响应请求")
            homeController.bannerList = item
        }
    }

    override fun initView() {
        super.initView()
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