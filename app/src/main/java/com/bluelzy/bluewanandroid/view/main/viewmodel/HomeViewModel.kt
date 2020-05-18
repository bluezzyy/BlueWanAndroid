package com.bluelzy.bluewanandroid.view.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.bluelzy.bluewanandroid.base.LiveCoroutinesViewModel
import com.bluelzy.bluewanandroid.model.DashboardArticleModel
import com.bluelzy.bluewanandroid.repository.MainRepository
import timber.log.Timber

class HomeViewModel constructor(
    private val mainRepository: MainRepository
) : LiveCoroutinesViewModel() {

    private var articleFetchingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    var articleLiveData: LiveData<DashboardArticleModel>
    var toastLiveData: MutableLiveData<String> = MutableLiveData()

    private var page: Int = 0

    init {
        Timber.d("injection MainViewModel")

        articleLiveData = launchOnViewModelScope {
            mainRepository.loadDashboardArticles(page) { toastLiveData.postValue(it) }
        }
    }

    fun loadMoreArticles() {
        articleLiveData = this.articleFetchingLiveData.switchMap {
            launchOnViewModelScope {
                mainRepository.loadDashboardArticles(++page) { toastLiveData.postValue(it) }
            }
        }
    }
}