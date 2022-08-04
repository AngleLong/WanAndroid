package com.angle.lib_home

import com.angle.lib_net.HiNet
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeRepositoryModule {

    @Singleton
    @Provides
    fun provideHomeApi(): HomeApi {
        //返回相应的API信息用来网络请求
        return HiNet.createRetrofitApi(HomeApi::class.java)
    }
}
