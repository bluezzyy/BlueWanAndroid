package com.bluelzy.bluewanandroid.view.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.bluelzy.bluewanandroid.base.LiveCoroutinesViewModel
import com.bluelzy.bluewanandroid.model.DashboardArticleModel
import com.bluelzy.bluewanandroid.repository.MainRepository
import timber.log.Timber

class HomeViewModel constructor(
    private val mainRepository: MainRepository
) : LiveCoroutinesViewModel() {

    private var articleFetchingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val articleLiveData : LiveData<DashboardArticleModel>
    val toastLiveData: MutableLiveData<String> = MutableLiveData()

    init {
        Timber.d("injection MainViewModel")

        // Get Data
        articleLiveData = this.articleFetchingLiveData.switchMap {
            launchOnViewModelScope {
                mainRepository.loadDashboardArticles { toastLiveData.postValue(it) }
            }
        }
    }

    fun fetchArticles() = articleFetchingLiveData.postValue(true)
}