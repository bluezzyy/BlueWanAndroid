package com.bluelzy.bluewanandroid.di

import com.bluelzy.bluewanandroid.view.category.viewmodel.CategoryViewModel
import com.bluelzy.bluewanandroid.view.main.viewmodel.HomeViewModel
import com.bluelzy.bluewanandroid.view.main.viewmodel.KnowledgeViewModel
import com.bluelzy.bluewanandroid.view.main.viewmodel.ProjectViewModel
import com.bluelzy.bluewanandroid.view.projectlist.viewmodel.ProjectListViewModel
import com.bluelzy.bluewanandroid.view.search.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      2020/4/10
 *   @desc
 */
val viewModelModule = module {

    viewModel { HomeViewModel(get()) }
    viewModel { KnowledgeViewModel(get()) }
    viewModel { ProjectViewModel(get()) }
    viewModel { CategoryViewModel(get()) }
    viewModel { ProjectListViewModel(get()) }
    viewModel { SearchViewModel(get()) }

}