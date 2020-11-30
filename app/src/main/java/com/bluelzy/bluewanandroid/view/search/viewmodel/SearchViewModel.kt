package com.bluelzy.bluewanandroid.view.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.bluelzy.bluewanandroid.base.LiveCoroutinesViewModel
import com.bluelzy.bluewanandroid.model.SearchAuthorModel
import com.bluelzy.bluewanandroid.repository.MainRepository
import org.koin.core.KoinComponent
import timber.log.Timber

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      11/26/2020
 *   @desc
 */
class SearchViewModel(
    private val mainRepository: MainRepository
) : LiveCoroutinesViewModel(), KoinComponent {

    private var searchFetchLiveData: MutableLiveData<Pair<Int, String>> = MutableLiveData()
    var searchLiveData: LiveData<SearchAuthorModel>
    var toastLiveData: MutableLiveData<String> = MutableLiveData()

    private var _showSpinnerLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val showSpinnerLiveData: LiveData<Boolean> = _showSpinnerLiveData

    private var page: Int = 0

    init {
        Timber.d("injection SearchViewModel")
        searchLiveData = this.searchFetchLiveData.switchMap {
            launchOnViewModelScope {
                _showSpinnerLiveData.postValue(true)
                mainRepository.searchByAuthor(it.first, it.second) {
                    _showSpinnerLiveData.postValue(false)
                    toastLiveData.postValue(it)
                }
            }
        }
    }

    /**
     *  [keyword] as the author name for search.
     */
    fun searchByAuthor(keyword: String) = searchFetchLiveData.postValue(Pair(0, keyword))

    /**
     * Load more data for the current [keyword]
     */
    fun loadMoreByAuthor(keyword: String) = searchFetchLiveData.postValue(Pair(page++, keyword))
}