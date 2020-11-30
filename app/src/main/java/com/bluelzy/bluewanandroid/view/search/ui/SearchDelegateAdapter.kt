package com.bluelzy.bluewanandroid.view.search.ui

import androidx.core.text.HtmlCompat
import com.bluelzy.bluewanandroid.R
import com.bluelzy.bluewanandroid.model.SearchAuthorItem
import com.chad.library.adapter.base.BaseDelegateMultiAdapter
import com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      2020/11/27
 *   @desc      Search Page Adapter
 */
class SearchDelegateAdapter : BaseDelegateMultiAdapter<SearchAuthorItem, BaseViewHolder>(),
    LoadMoreModule {

    init {
        setMultiTypeDelegate(object : BaseMultiTypeDelegate<SearchAuthorItem>() {
            override fun getItemType(data: List<SearchAuthorItem>, position: Int): Int =
                VIEW_TYPE_ARTICLE
        })

        getMultiTypeDelegate()
            ?.addItemType(VIEW_TYPE_ARTICLE, R.layout.item_dashboard_article)
            .also { this.addChildClickViewIds(R.id.iv_favourite_article) }
    }

    override fun convert(holder: BaseViewHolder, item: SearchAuthorItem) {
        when (holder.itemViewType) {
            VIEW_TYPE_ARTICLE -> setupArticleType(holder, item)
        }
    }

    private fun setupArticleType(holder: BaseViewHolder, item: SearchAuthorItem) {
        with(holder) {
            setText(R.id.tv_article_title, HtmlCompat.fromHtml(item.title, 0))
            setText(R.id.tv_article_share_user, String.format(context.getString(R.string.dashboard_author, item.author)))
            setText(R.id.tv_article_time, String.format(context.getString(R.string.dashboard_share_time), item.niceDate))
        }
    }

    companion object {
        const val VIEW_TYPE_ARTICLE = 0
    }
}