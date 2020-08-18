package com.bluelzy.bluewanandroid.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      2020/4/9
 *   @desc
 */
abstract class LiveCoroutinesViewModel: ViewModel() {

    inline fun<T> launchOnViewModelScope(crossinline block: suspend () -> LiveData<T>) : LiveData<T> {
        return liveData(viewModelScope.coroutineContext+ Dispatchers.IO) {
            emitSource(block())
        }
    }
}