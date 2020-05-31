package com.bluelzy.bluewanandroid.repository

import androidx.lifecycle.MutableLiveData
import com.bluelzy.bluewanandroid.model.DashboardArticleModel
import com.bluelzy.bluewanandroid.network.ApiResponse
import com.bluelzy.bluewanandroid.network.MainClient
import com.bluelzy.bluewanandroid.network.message
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      2020/4/9
 *   @desc
 */
class MainRepository constructor(
    private val mainClient: MainClient
) : Repository {

    override var isLoading: Boolean = false

    init {
        Timber.d("Injection MainRepository")
    }

    suspend fun loadDashboardArticles(
        page: Int,
        success: (DashboardArticleModel) -> Unit,
        error: (String) -> Unit
    ) = withContext(Dispatchers.IO) {
        val liveData = MutableLiveData<DashboardArticleModel>()
        var articles = DashboardArticleModel()
        isLoading = true
        mainClient.fetchDashboardArticles(page) { response ->
            isLoading = false
            when (response) {
                is ApiResponse.Success -> {
                    response.data?.let {
                        articles = it
                        success(it)
                    }
                }
                is ApiResponse.Failure.Error -> error(response.message())
                is ApiResponse.Failure.Exception -> error(response.message())
            }
        }
        liveData.apply { this.postValue(articles) }
    }
}