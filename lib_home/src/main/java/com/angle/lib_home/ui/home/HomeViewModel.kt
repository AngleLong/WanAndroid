package com.angle.lib_home.ui.home

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.angle.lib_common.bean.BaseModel
import com.angle.lib_common.utils.loadDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {

    private val _bannerModel = BaseModel<List<HomeBannerBean>>()

    private val _bannerModelNew = MutableLiveData<List<HomeBannerBean>>()

    var bannerModel: MutableLiveData<List<HomeBannerBean>> = _bannerModelNew

    private val _homeList = BaseModel<HomeListBean>()
    val homeList = _homeList

    fun requestBanner() {
        loadDataState(_bannerModel, loader = { homeRepository.requestBanner() })
    }
//
//    fun  requestList(): LiveData<List<Data>>{
//        loadDataState(homeList, loader = { homeRepository.requestHomeListLiveData() })
//    }
//
    fun requestListFlow(): Flow<PagingData<Data>> {
        return homeRepository.requestHomeListFlow().cachedIn(viewModelScope)
//        loadDataState(_homeList, loader = { homeRepository.requestHomeList(pager) })
    }

    fun requestListLivedata(): LiveData<PagingData<Data>> {
        return homeRepository.requestHomeListLiveData().cachedIn(viewModelScope)
    }




    /**
     * 方式1: 获取banner对象
     */
    fun requestBannerFlow() {
        viewModelScope.launch {
            homeRepository.requestBannerFlow()
                .collectLatest {
                    bannerModel.postValue(it)
                }
        }
    }

//    /**
//     *  方式二: 获取banner对象
//     */
//    fun requestBannerFlow() = liveData {
//        homeRepository.requestBannerFlow()
//            .collectLatest {
//                emit(it)
//            }
//    }

//    /**
//     * 方式三: 获取banner对象
//     */
//    fun requestBannerFlow() = homeRepository.requestBannerFlow()
//        .onStart {
//            Log.e("requestBannerFlow", "onStart: 开始请求")
//        }
//        .catch {
//            Log.e("requestBannerFlow", "catch: 出现异常")
//        }
//        .onCompletion {
//            Log.e("requestBannerFlow", "onCompletion: 请求完成")
//        }
//        .asLiveData()

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


    fun testAddNotificationItem() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                com.angle.lib_db.AppDatabase.getAppDatabase().messageDao()
                    .insertAll(com.angle.lib_db.MessageBean("这是一条消息 ${Random.nextLong()}"))
            } catch (e: Exception) {
                Log.e("TAG", "testAddNotificationItem: 插入异常 $e")
            }
        }
    }
}
