package com.angle.lib_home

import com.angle.lib_net.RetrofitFactory
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
        return RetrofitFactory.getDefaultService(HomeConfigUtils.baseUrl,
            clazz = HomeApi::class.java)
    }
}
