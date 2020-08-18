package com.bluelzy.bluewanandroid.di

import com.bluelzy.bluewanandroid.network.BlueWanAndroidService
import com.bluelzy.bluewanandroid.network.MainClient
import com.bluelzy.bluewanandroid.network.RequestInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      2020/4/8
 *   @desc
 */

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(RequestInterceptor())
            .addNetworkInterceptor(HttpLoggingInterceptor())
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get<OkHttpClient>())
            .baseUrl("https://www.wanandroid.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(BlueWanAndroidService::class.java) }

    single { MainClient(get()) }

}