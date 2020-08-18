package com.bluelzy.bluewanandroid.view.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.bluelzy.bluewanandroid.base.LiveCoroutinesViewModel
import com.bluelzy.bluewanandroid.model.KnowledgeModel
import com.bluelzy.bluewanandroid.repository.MainRepository
import com.bluelzy.bluewanandroid.repository.SharedPreferencesRepository
import timber.log.Timber

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      2020/4/8
 *   @desc
 */

class KnowledgeViewModel(private val mainRepository: MainRepository) : LiveCoroutinesViewModel() {

    private var knowledgeFetchingLiveData: MutableLiveData<Unit> = MutableLiveData()
    var knowledgeLiveData: LiveData<KnowledgeModel> = MutableLiveData()
    var toastLiveData: MutableLiveData<String> = MutableLiveData()

    private var _localKnowledgeData = MutableLiveData<Unit>()
    val localKnowledgeData: LiveData<Unit> = _localKnowledgeData

    init {
        Timber.d("injection KnowledgeViewModel")

        knowledgeLiveData = this.knowledgeFetchingLiveData.switchMap {
            launchOnViewModelScope {
                mainRepository.loadKnowledgeJson { toastLiveData.postValue(it) }
            }
        }
        initKnowledgeJson()
    }

    private fun initKnowledgeJson() {
        val lastUpdateTime = SharedPreferencesRepository.getKnowledgeUpdateTime()
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastUpdateTime > UPDATE_PERIOD || SharedPreferencesRepository.getKnowledgeJsonData().isEmpty()) {
            Timber.tag("BlueLzy").d("get knowledge from network")
            SharedPreferencesRepository.setKnowledgeUpdateTime(currentTime)
            getNetworkJson()
        } else {
            Timber.tag("BlueLzy").d("get knowledge from local")
            getLocalJsonFile()
        }
    }

    private fun getNetworkJson() = knowledgeFetchingLiveData.postValue(Unit)

    private fun getLocalJsonFile() = _localKnowledgeData.postValue(Unit)

    companion object {
        const val UPDATE_PERIOD = 7 * 24 * 60 * 60 * 1000 // 7 days
    }

}