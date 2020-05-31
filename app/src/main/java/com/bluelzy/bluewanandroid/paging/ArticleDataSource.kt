package com.bluelzy.bluewanandroid.paging

import androidx.paging.PageKeyedDataSource
import com.bluelzy.bluewanandroid.model.Article
import com.bluelzy.bluewanandroid.repository.MainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      2020/5/30
 *   @desc      DataSource for HomeFragment's Data and fetch by the `page` number.
 */
class ArticleDataSource(private val scope: CoroutineScope) : PageKeyedDataSource<Int, Article>(),
    KoinComponent {

    private val mainRepository: MainRepository by inject()
    private var page = 1

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Article>
    ) {
        scope.launch {
            mainRepository.loadDashboardArticles(
                page,
                {
                    val articles = it.data?.articles ?: listOf()
                    callback.onResult(articles, null, articles.size)
                },
                {
                    // Handle Error
                })
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
        scope.launch {
            mainRepository.loadDashboardArticles(
                ++page,
                {
                    Timber.d("load article data from page: -------- $page ------------ data: $it \n")
                    val articles = it.data?.articles ?: listOf()
                    callback.onResult(articles, articles.size)
                },
                {
                    // Handle Error
                })
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
        // ignored, since we only ever append to our initial load
    }

    override fun invalidate() {
        super.invalidate()
        scope.cancel()
    }
}