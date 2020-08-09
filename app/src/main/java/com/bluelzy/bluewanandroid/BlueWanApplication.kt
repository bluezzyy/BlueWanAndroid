package com.bluelzy.bluewanandroid

import android.app.Application
import com.bluelzy.bluewanandroid.di.networkModule
import com.bluelzy.bluewanandroid.di.repositoryModule
import com.bluelzy.bluewanandroid.di.viewModelModule
import com.bluelzy.bluewanandroid.repository.SharedPreferencesRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

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

        SharedPreferencesRepository.init(this)

        if (BuildConfig.DEBUG) {
            // logging
            Timber.plant(Timber.DebugTree())
        }
    }

}