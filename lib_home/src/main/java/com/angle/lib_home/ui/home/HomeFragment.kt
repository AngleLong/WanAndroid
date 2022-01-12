package com.angle.lib_home.ui.home

import androidx.fragment.app.viewModels
import com.angle.lib_common.base.BaseFragment
import com.angle.lib_home.HomeController
import com.angle.lib_home.R
import com.angle.lib_home.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val homeViewModel by viewModels<HomeViewModel>()

    @Inject
    lateinit var homeController: HomeController

    override fun configLayoutRes(): Int = R.layout.fragment_home

    override fun initConfig() {
        homeViewModel.bannerModel.data.observe(this) { item ->
            homeController.bannerList = item
        }

        homeViewModel.homeList.data.observe(this) { homeListBean ->
            homeController.itemList = homeListBean?.datas
        }
    }

    override fun initView() {
        super.initView()
        dataBinding?.showRv?.setController(homeController)
    }

    override fun initData() {
        super.initData()
        homeViewModel.requestBanner()
        homeViewModel.requestList(0)
    }
}