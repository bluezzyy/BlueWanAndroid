package com.bluelzy.bluewanandroid.di

import com.bluelzy.bluewanandroid.view.main.viewmodel.HomeViewModel
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      2020/4/10
 *   @desc
 */
val viewModelModule = module {

    viewModel { HomeViewModel(get()) }

}