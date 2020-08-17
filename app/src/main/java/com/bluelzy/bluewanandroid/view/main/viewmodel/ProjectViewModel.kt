package com.bluelzy.bluewanandroid.view.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.bluelzy.bluewanandroid.base.LiveCoroutinesViewModel
import com.bluelzy.bluewanandroid.model.ProjectModel
import com.bluelzy.bluewanandroid.repository.MainRepository
import timber.log.Timber

class ProjectViewModel constructor(private val mainRepository: MainRepository) : LiveCoroutinesViewModel() {

    private var projectFetchingLiveData: MutableLiveData<Unit> = MutableLiveData()
    var projectLiveData: LiveData<ProjectModel> = MutableLiveData()
    var toastLiveData: MutableLiveData<String> = MutableLiveData()

    init {
        Timber.d("injection ProjectViewModel")

        projectLiveData = this.projectFetchingLiveData.switchMap {
            launchOnViewModelScope {
                mainRepository.loadProjectJson { toastLiveData.postValue(it) }
            }
        }
    }

    fun fetchProjectJson() = projectFetchingLiveData.postValue(Unit)
}