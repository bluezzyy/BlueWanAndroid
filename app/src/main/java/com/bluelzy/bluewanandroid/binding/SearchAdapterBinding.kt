package com.bluelzy.bluewanandroid.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bluelzy.bluewanandroid.model.SearchAuthorItem
import com.bluelzy.bluewanandroid.utils.whatIfNotNullOrEmpty
import com.bluelzy.bluewanandroid.view.detail.ui.WebViewActivity
import com.bluelzy.bluewanandroid.view.search.ui.SearchDelegateAdapter
import com.chad.library.adapter.base.BaseDelegateMultiAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      2020/11/28
 *   @desc      DataBinding function for the Search Page
 */

@BindingAdapter("adapterSearchList")
fun bindAdapterSearchList(
    view: RecyclerView,
    result: List<SearchAuthorItem>?
) {
    result.whatIfNotNullOrEmpty {
        (view.adapter as SearchDelegateAdapter).run {
            loadMoreModule.loadMoreComplete()
            addData(it)
        }
    }
}

@BindingAdapter("searchItemClick")
fun bindSearchItemClick(
    view: RecyclerView,
    adapter: BaseDelegateMultiAdapter<SearchAuthorItem, BaseViewHolder>
) {
    adapter.setOnItemClickListener { _, itemView, position ->
        WebViewActivity.newInstance(
            view.context,
            itemView,
            adapter.getItem(position).link,
            adapter.getItem(position).title)
    }
}