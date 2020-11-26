package com.bluelzy.bluewanandroid.view.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.bluelzy.bluewanandroid.base.LiveCoroutinesViewModel
import com.bluelzy.bluewanandroid.model.ProjectModel
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

    private var searchFetchLiveData: MutableLiveData<String> = MutableLiveData()
    var searchLiveData: LiveData<ProjectModel>
    var toastLiveData: MutableLiveData<String> = MutableLiveData()

    init {
        Timber.d("injection SearchViewModel")
        searchLiveData = this.searchFetchLiveData.switchMap {
            launchOnViewModelScope {
                mainRepository.searchByAuthor(it) { toastLiveData.postValue(it) }
            }
        }
    }

    /**
     *  [keyword] as the author name for search.
     */
    fun searchByAuthor(keyword: String) = searchFetchLiveData.postValue(keyword)
}