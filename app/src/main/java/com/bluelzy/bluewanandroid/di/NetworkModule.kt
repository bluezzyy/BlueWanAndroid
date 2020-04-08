package com.bluelzy.bluewanandroid.di

import com.bluelzy.bluewanandroid.main.view.MainActivity
import com.bluelzy.bluewanandroid.main.viewmodel.HomeViewModel
import com.bluelzy.bluewanandroid.network.RequestInterceptor
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent.getKoin

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
            .build()
    }

    scope(named<MainActivity>()) {
        scoped { HomeViewModel() }
    }


}