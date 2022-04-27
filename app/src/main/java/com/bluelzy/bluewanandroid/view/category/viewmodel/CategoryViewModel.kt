package com.bluelzy.bluewanandroid.view.category.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.bluelzy.bluewanandroid.base.LiveCoroutinesViewModel
import com.bluelzy.bluewanandroid.model.DashboardArticleModel
import com.bluelzy.bluewanandroid.repository.MainRepository
import org.koin.core.component.KoinComponent
import timber.log.Timber

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      2020/8/16
 *   @desc
 */
class CategoryViewModel constructor(
    private val mainRepository: MainRepository
) : LiveCoroutinesViewModel(), KoinComponent {

    private var articleFetchingLiveData: MutableLiveData<Int> = MutableLiveData()
    var articleLiveData: LiveData<DashboardArticleModel>
    var toastLiveData: MutableLiveData<String> = MutableLiveData()

    private var page: Int = 0
    private var cid: Int = 0

    init {
        Timber.d("injection CategoryViewModel")
        articleLiveData = this.articleFetchingLiveData.switchMap {
            launchOnViewModelScope {
                mainRepository.loadCategoryArticles(cid, it) { toastLiveData.postValue(it) }
            }
        }
    }

    fun fetchArticles(cid: Int) {
        this.cid = cid
        this.articleFetchingLiveData.postValue(page++)
    }
}