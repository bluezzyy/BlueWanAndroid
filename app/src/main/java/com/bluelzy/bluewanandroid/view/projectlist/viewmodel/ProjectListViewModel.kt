package com.bluelzy.bluewanandroid.view.projectlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.bluelzy.bluewanandroid.base.LiveCoroutinesViewModel
import com.bluelzy.bluewanandroid.model.ProjectItemModel
import com.bluelzy.bluewanandroid.repository.MainRepository
import org.koin.core.KoinComponent
import timber.log.Timber

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      2020/8/20
 *   @desc
 */
class ProjectListViewModel(
    private val mainRepository: MainRepository
) : LiveCoroutinesViewModel(), KoinComponent {

    private var projectListFetchingLiveData: MutableLiveData<Int> = MutableLiveData()
    var projectListLiveData: LiveData<ProjectItemModel>
    var toastLiveData: MutableLiveData<String> = MutableLiveData()

    private var cid: Int = 0
    private var page: Int = 1

    init {
        Timber.d("injection ProjectListViewModel")

        projectListLiveData = this.projectListFetchingLiveData.switchMap {
            launchOnViewModelScope {
                mainRepository.loadProjectList(it, cid) { toastLiveData.postValue(it) }
            }
        }
    }

    fun fetchProjects(cid: Int) {
        this.cid = cid
        this.projectListFetchingLiveData.postValue(page++)
    }
}