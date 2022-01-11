package com.angle.lib_home.ui.home

import com.angle.lib_home.HomeApi
import javax.inject.Inject

class HomeRepository @Inject constructor(private val homeApi: HomeApi) {
    suspend fun requestBanner(): List<HomeBannerBean> = homeApi.banner()

    suspend fun requestHomeList(pager: Int): HomeListBean = homeApi.homeList(pager)
}