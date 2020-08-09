package com.bluelzy.bluewanandroid.binding

import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bluelzy.bluewanandroid.utils.whatIfNotNull
import com.chad.library.adapter.base.BaseDelegateMultiAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      2020/4/6
 *   @desc
 */

@BindingAdapter("toast")
fun bindToast(view: RecyclerView, text: LiveData<String>) {
    text.value.whatIfNotNull {
        Toast.makeText(view.context, it, Toast.LENGTH_LONG).show()
    }
}

@BindingAdapter("adapter")
fun bindAdapter(view: RecyclerView, adapter: BaseDelegateMultiAdapter<Any, BaseViewHolder>) {
    view.adapter = adapter
}

