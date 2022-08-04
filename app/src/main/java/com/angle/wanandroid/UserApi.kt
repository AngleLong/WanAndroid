package com.angle.wanandroid

import com.angle.lib_net.HiNet
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import javax.inject.Singleton

interface UserApi {

    /**
     * 登陆
     */
    @FormUrlEncoded
    @POST("/user/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String,
    ): String

    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("/user/register")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") rePassword: String,
    ): String
}

@Module
@InstallIn(SingletonComponent::class)
object UserApiModule {

    @Singleton
    @Provides
    fun provideLoginApi(): UserApi {
        return HiNet.createRetrofitApi(UserApi::class.java)
    }
}
