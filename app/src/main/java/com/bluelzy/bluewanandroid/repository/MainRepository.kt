package com.bluelzy.bluewanandroid.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.bluelzy.bluewanandroid.model.*
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

    suspend fun loadDashboardBanner(
        succeed: (BannerModel) -> Unit,
        error: (String) -> Unit
    ) = withContext(Dispatchers.IO) {
        val liveData = MutableLiveData<BannerModel>()
        var banner = BannerModel()
        mainClient.fetchDashboardBanner { response ->
            when (response) {
                is ApiResponse.Success -> {
                    response.data?.let {
                        banner = it
                        succeed(it)
                    }
                }
                is ApiResponse.Failure.Error -> error(response.message())
                is ApiResponse.Failure.Exception -> error(response.message())
            }
        }

        liveData.apply { this.postValue(banner) }
    }

    suspend fun loadDashboardArticles(
        page: Int,
        error: (String) -> Unit
    ) = withContext(Dispatchers.IO) {
        val liveData = MutableLiveData<DashboardArticleModel>()
        var articles = DashboardArticleModel()
        mainClient.fetchDashboardArticles(page) { response ->
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
        mainClient.fetchKnowledgeJson { response ->
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
        mainClient.fetchKnowledgeCategoryArticles(cid, page) { response ->
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
        mainClient.fetchProjectJson { response ->
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

    suspend fun loadProjectList(page: Int, cid: Int, error: (String) -> Unit) =
        withContext(Dispatchers.IO) {
            val liveData = MutableLiveData<ProjectItemModel>()
            var json = ProjectItemModel()
            mainClient.fetchProjectList(page, cid) { response ->
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

    suspend fun searchByAuthor(page: Int, author: String, error: (String) -> Unit) =
        withContext(Dispatchers.IO) {
            val liveData = MutableLiveData<SearchAuthorModel>()
            var json = SearchAuthorModel()

            mainClient.searchByAuthor(page, author) { response ->
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