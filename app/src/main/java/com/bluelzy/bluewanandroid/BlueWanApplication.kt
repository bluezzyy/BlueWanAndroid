package com.bluelzy.bluewanandroid

import android.app.Application
import com.bluelzy.bluewanandroid.di.networkModule
import com.bluelzy.bluewanandroid.di.repositoryModule
import com.bluelzy.bluewanandroid.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      2020/4/6
 *   @desc
 */
class BlueWanApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BlueWanApplication)
            modules(networkModule)
            modules(viewModelModule)
            modules(repositoryModule)
        }

        if (BuildConfig.DEBUG) {
            // logging
        }
    }

}