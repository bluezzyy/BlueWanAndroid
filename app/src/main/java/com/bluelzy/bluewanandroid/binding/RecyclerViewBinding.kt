package com.bluelzy.bluewanandroid.binding

import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bluelzy.bluewanandroid.adapter.HomeDelegateMultiAdapter
import com.bluelzy.bluewanandroid.model.Article
import com.bluelzy.bluewanandroid.utils.whatIfNotNull
import com.bluelzy.bluewanandroid.utils.whatIfNotNullOrEmpty
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
fun bindAdapter(view: RecyclerView, adapter: BaseDelegateMultiAdapter<Article, BaseViewHolder>) {
    view.adapter = adapter
}

@BindingAdapter("adapterArticleList")
fun bindAdapterPosterLIst(view: RecyclerView, article: List<Article>?) {
    article.whatIfNotNullOrEmpty {
        (view.adapter as? HomeDelegateMultiAdapter)?.setList(it)
    }
}

