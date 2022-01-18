package com.angle.lib_home.ui.home

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.angle.lib_home.HomeApi
import java.lang.Exception
import java.lang.RuntimeException
import java.util.*

/**
 * 1. 创建提供数据源的PagingSource对象,主要是提供数据源
 */
class HomePagingSource(private val homeApi: HomeApi) : PagingSource<Int, Data>() {
    override fun getRefreshKey(state: PagingState<Int, Data>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        return try {

            //这个是页数
            val page = params.key ?: 1
            //每页的页数
            val pageSize = params.loadSize
            //相应请求的方法
            val repoResponse = homeApi.homeList(page)
            //请求得到的数据
            val repoItems = repoResponse.datas
            //上一页的索引
            val prevKey = if (page > 1) page - 1 else null
            //下一页的索引
            val nextKey = if (repoItems.isNotEmpty()) page + 1 else null

            //结果成功的回调
            LoadResult.Page(repoItems, prevKey, nextKey)
        } catch (e: Exception) {
            //异常情况的回调
            LoadResult.Error(e)
        }
    }
}

class PagingListSource<T : Any>(private val fetchList: suspend (page: Int) -> List<T>) :
    PagingSource<Int, T>() {
    override fun getRefreshKey(state: PagingState<Int, T>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        return try {
            //这个是页数
            val page = params.key ?: 1
            //每页的页数
            val pageSize = params.loadSize
            //相应请求的方法
            val repoItems = fetchList.invoke(page)
            //上一页的索引
            val prevKey = if (page > 1) page - 1 else null
            //下一页的索引
            val nextKey = if (repoItems.isNotEmpty()) page + 1 else null

            //结果成功的回调
            LoadResult.Page(repoItems, prevKey, nextKey)
        } catch (e: Exception) {
            //异常情况的回调
            LoadResult.Error(e)
        }
    }
}