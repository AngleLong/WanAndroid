package com.angle.lib_login

import com.angle.lib_net.HiNet
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginRepositoryModule {

    @Singleton
    @Provides
    fun provideLoginApi(): LoginApi {
        return HiNet.createRetrofitApi(LoginApi::class.java)
    }
}
