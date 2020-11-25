package com.bluelzy.bluewanandroid.view.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.bluelzy.bluewanandroid.base.LiveCoroutinesViewModel
import com.bluelzy.bluewanandroid.model.BannerModel
import com.bluelzy.bluewanandroid.model.DashboardArticleModel
import com.bluelzy.bluewanandroid.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import timber.log.Timber

class HomeViewModel constructor(
    private val mainRepository: MainRepository
) : LiveCoroutinesViewModel(), KoinComponent {

    private var articleFetchingLiveData: MutableLiveData<Int> = MutableLiveData()
    var articleLiveData: LiveData<DashboardArticleModel>
    var toastLiveData: MutableLiveData<String> = MutableLiveData()

    private var bannerFetchLiveData: MutableLiveData<BannerModel> = MutableLiveData()
    val bannerLiveData: LiveData<BannerModel>
        get() = bannerFetchLiveData

    private var page: Int = 0

    init {
        Timber.d("injection HomeViewModel")
        articleLiveData = this.articleFetchingLiveData.switchMap {
            launchOnViewModelScope {
                mainRepository.loadDashboardArticles(it) { toastLiveData.postValue(it) }
            }
        }
    }

    fun fetchArticles() = this.articleFetchingLiveData.postValue(page++)

    fun fetchBanner() {
        viewModelScope.launch(Dispatchers.IO) {
            mainRepository.loadDashboardBanner({
                bannerFetchLiveData.postValue(it)
            }, {
                toastLiveData.postValue(it)
            })
        }
    }
}