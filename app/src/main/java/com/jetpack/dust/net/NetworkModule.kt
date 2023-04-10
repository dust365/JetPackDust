package com.jetpack.dust.net

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
//@InstallIn(FragmentComponent::class)
@InstallIn(SingletonComponent::class)
class NetworkModule {


    //Singleton  实际应用场景需要使用单例子 需要替换到SingletonComponent 作用域上
    @Singleton
    @Provides
    fun provideOkhttpClient():OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .build()

    }

    //一般提供使用binds 使用构造指定的芳芳   而 三方库使用Provides
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://zhewendev.github.io/")
            .client(okHttpClient)
            .build()
    }

}