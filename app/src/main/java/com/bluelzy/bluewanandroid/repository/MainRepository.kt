package com.bluelzy.bluewanandroid.repository

import androidx.lifecycle.MutableLiveData
import com.bluelzy.bluewanandroid.model.DashboardArticleModel
import com.bluelzy.bluewanandroid.model.KnowledgeModel
import com.bluelzy.bluewanandroid.model.ProjectModel
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
                        liveData.postValue(it)
                    }
                }
                is ApiResponse.Failure.Error -> error(response.message())
                is ApiResponse.Failure.Exception -> error(response.message())
            }
        }
        liveData.apply { this.postValue(articles) }
    }

    suspend fun loadKnowledgeJson(error: (String) -> Unit) = withContext(Dispatchers.IO) {
        val liveData = MutableLiveData<KnowledgeModel>()
        var json = KnowledgeModel()
        isLoading = true
        mainClient.fetchKnowledgeJson { response ->
            isLoading = false
            when (response) {
                is ApiResponse.Success -> {
                    response.data?.let {
                        json = it
                        liveData.postValue(it)
                    }
                }
                is ApiResponse.Failure.Error -> error(response.message())
                is ApiResponse.Failure.Exception -> error(response.message())
            }
        }
        liveData.apply { this.postValue(json) }
    }

    suspend fun loadCategoryArticles(
        cid: Int,
        page: Int,
        error: (String) -> Unit
    ) = withContext(Dispatchers.IO) {
        val liveData = MutableLiveData<DashboardArticleModel>()
        var articles = DashboardArticleModel()
        isLoading = true
        mainClient.fetchKnowledgeCategoryArticles(cid, page) { response ->
            isLoading = false
            when (response) {
                is ApiResponse.Success -> {
                    response.data?.let {
                        articles = it
                        liveData.postValue(it)
                    }
                }
                is ApiResponse.Failure.Error -> error(response.message())
                is ApiResponse.Failure.Exception -> error(response.message())
            }
        }
        liveData.apply { this.postValue(articles) }
    }

    suspend fun loadProjectJson(error: (String) -> Unit) = withContext(Dispatchers.IO) {
        val liveData = MutableLiveData<ProjectModel>()
        var json = ProjectModel()
        isLoading = true
        mainClient.fetchProjectJson { response ->
            isLoading = false
            when (response) {
                is ApiResponse.Success -> {
                    response.data?.let {
                        json = it
                        liveData.postValue(it)
                    }
                }
                is ApiResponse.Failure.Error -> error(response.message())
                is ApiResponse.Failure.Exception -> error(response.message())
            }
        }
        liveData.apply { this.postValue(json) }
    }


}