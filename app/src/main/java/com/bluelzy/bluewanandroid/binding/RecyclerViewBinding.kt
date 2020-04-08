package com.bluelzy.bluewanandroid.binding

import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      2020/4/6
 *   @desc
 */

@BindingAdapter("toast")
fun bindToast(view: RecyclerView, text: LiveData<String>) {
    text.value
        .takeIf { !it.isNullOrBlank() }
        ?.run {
            Toast.makeText(view.context, this, Toast.LENGTH_LONG).show()
        }
}

