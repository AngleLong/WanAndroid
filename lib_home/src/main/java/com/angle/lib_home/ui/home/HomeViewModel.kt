package com.angle.lib_home.ui.home

import androidx.lifecycle.ViewModel
import com.angle.lib_common.bean.BaseModel
import com.angle.lib_common.utils.loadDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {

    private val _bannerModel = BaseModel<List<HomeBannerBean>>()
    val bannerModel = _bannerModel

    private val _homeList = BaseModel<HomeListBean>()
    val homeList = _homeList

    fun requestBanner() {
        loadDataState(_bannerModel, loader = { homeRepository.requestBanner() })
    }

    fun requestList(pager: Int) {
        loadDataState(_homeList, loader = { homeRepository.requestHomeList(pager) })
    }

//    //todo 这里其实可以两个一起请求的 但是这里考虑到底部分页和顶部banner不应该每次都一起请求,所以这里就不这么处理了
//    private val _homeModel = BaseModel<Pair<List<HomeBannerBean>, HomeListBean>>()
//    val homeModel = _homeModel
//
//    fun requestHomeData(pager: Int) {
//        loadDataState(
//            _homeModel,
//            loader = {
//                val bannerResult = homeRepository.requestBanner()
//                val itemListResult = homeRepository.requestHomeList(pager)
//                Pair(bannerResult, itemListResult)
//            }
//        )
//    }
}
