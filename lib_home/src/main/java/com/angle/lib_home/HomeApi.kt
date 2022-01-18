package com.angle.lib_home

import com.angle.lib_home.ui.home.HomeBannerBean
import com.angle.lib_home.ui.home.HomeListBean
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeApi {
    /**
     * 获取Banner
     */
    @GET("/banner/json")
    suspend fun banner(): List<HomeBannerBean>


    /**
     * 获取首页列表
     */
    @GET("article/list/{pager}/json")
    suspend fun homeList(@Path("pager") pager: Int): HomeListBean
}