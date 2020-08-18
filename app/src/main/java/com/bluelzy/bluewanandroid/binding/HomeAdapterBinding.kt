package com.bluelzy.bluewanandroid.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bluelzy.bluewanandroid.R
import com.bluelzy.bluewanandroid.model.Article
import com.bluelzy.bluewanandroid.model.ProjectModelItem
import com.bluelzy.bluewanandroid.utils.whatIfNotNullOrEmpty
import com.bluelzy.bluewanandroid.view.detail.ui.ArticleDetailActivity
import com.bluelzy.bluewanandroid.view.main.adapter.home.HomeDelegateMultiAdapter
import com.bluelzy.bluewanandroid.view.main.adapter.project.ProjectDelegateMultiAdapter
import com.chad.library.adapter.base.BaseDelegateMultiAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import timber.log.Timber

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      2020/8/8
 *   @desc      DataBinding Adapter for Home Page
 */

@BindingAdapter("articleItemClick")
fun bindArticleItemClick(
    view: RecyclerView,
    adapter: BaseDelegateMultiAdapter<Article, BaseViewHolder>
) {
    adapter.setOnItemClickListener { _, _, position ->
        ArticleDetailActivity.newInstance(
            view.context,
            adapter.getViewByPosition(position, R.id.cl_item_article),
            adapter.getItem(position)
        )
    }
    adapter.setOnItemChildClickListener { _, itemView, _ ->
        if (itemView.id == R.id.iv_favourite_article) {
            // TODO: 添加收藏功能
            Timber.tag("BlueLzy").d("favourite clicked...")
        }
    }
}

@BindingAdapter("adapterArticleList")
fun bindAdapterArticleList(view: RecyclerView, article: List<Article>?) {
    article.whatIfNotNullOrEmpty {
        (view.adapter as? HomeDelegateMultiAdapter)?.run {
            loadMoreModule.loadMoreComplete()
            addData(it)
        }
    }
}

@BindingAdapter("adapterProjectList")
fun bindAdapterProjectList(view: RecyclerView, article: List<ProjectModelItem>?) {
    article.whatIfNotNullOrEmpty {
        (view.adapter as? ProjectDelegateMultiAdapter)?.run {
            addData(it)
        }
    }
}

