package com.angle.lib_home.ui.home

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.angle.lib_home.HomeApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepository @Inject constructor(private val homeApi: HomeApi) {
    suspend fun requestBanner(): List<HomeBannerBean> = homeApi.banner()

    //网络请求中实体转换成Flow对象
    fun requestBannerFlow(): Flow<List<HomeBannerBean>> = flow {
        val banner = homeApi.banner()
        emit(banner)
    }

//    fun  requestHomeList(pager):HomeListBean = homeApi.homeList(pager = )

    //    suspend fun requestHomeList(pager: Int): HomeListBean = homeApi.homeList(pager)
    fun requestHomeListFlow(): Flow<PagingData<Data>> {
        return Pager(config = PagingConfig(20),
            pagingSourceFactory = {
                HomePagingSource(homeApi)
            }).flow
    }

//    fun requestHomeListFlow(): Flow<PagingData<Data>> {
//        return Pager(config = PagingConfig(20), pagingSourceFactory = {
//            PagingListSource {
//                homeApi.homeList(it).datas
//            }
//        }).flow
//    }

    fun requestHomeListLiveData(): LiveData<PagingData<Data>> {
        return Pager(config = PagingConfig(20),
            pagingSourceFactory = {
                HomePagingSource(homeApi)
            }).liveData
    }
}

//public class ContextUtils {
//    public static Application getApplication(){
//        try {
//            Method method = Class.forName("android.app.ActivityThread").getDeclaredMethod("currentApplication");
//            return (Application) method.invoke(null, null);
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//}