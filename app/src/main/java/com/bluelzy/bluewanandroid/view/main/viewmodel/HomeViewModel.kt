package com.bluelzy.bluewanandroid.view.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.bluelzy.bluewanandroid.base.LiveCoroutinesViewModel
import com.bluelzy.bluewanandroid.model.Article
import com.bluelzy.bluewanandroid.paging.ArticleDataSource
import timber.log.Timber

class HomeViewModel : LiveCoroutinesViewModel() {

    var postListData: LiveData<PagedList<Article>>

    init {
        Timber.d("injection MainViewModel")

        val config = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()

        postListData = initializedPagedListBuilder(config).build()
    }

    private fun initializedPagedListBuilder(config: PagedList.Config): LivePagedListBuilder<Int, Article> {
        val dataSource = object : DataSource.Factory<Int, Article>() {
            override fun create(): DataSource<Int, Article> {
                return ArticleDataSource(viewModelScope)
            }
        }
        return LivePagedListBuilder(dataSource, config)
    }

    companion object {
        private const val PAGE_SIZE = 10
        private const val PREFETCH_DISTANCE = 3
    }
}