package com.bluelzy.bluewanandroid.adapter

import com.bluelzy.bluewanandroid.R
import com.bluelzy.bluewanandroid.model.Article
import com.chad.library.adapter.base.BaseDelegateMultiAdapter
import com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 *   @author : BlueLzy
 *   @email  : bluehobert@gmail.com
 *   @date   : 2020/05/17
 *   @desc   : Dashboard Multi Item Type Adapter
 */
class HomeDelegateMultiAdapter : BaseDelegateMultiAdapter<Article, BaseViewHolder>() {

    init {
        setMultiTypeDelegate(object : BaseMultiTypeDelegate<Article>() {
            override fun getItemType(data: List<Article>, position: Int): Int {
                return VIEW_TYPE_ARTICLE
            }
        })

        getMultiTypeDelegate()
            ?.addItemType(VIEW_TYPE_ARTICLE, R.layout.item_dashboard_article)
    }

    override fun convert(holder: BaseViewHolder, item: Article) {
        when (holder.itemViewType) {
            VIEW_TYPE_ARTICLE -> setupArticleType(holder, item)
        }
    }

    private fun setupArticleType(holder: BaseViewHolder, item: Article) =
        with(holder) {
            setText(R.id.tv_article_title, item.title)
            setText(
                R.id.tv_article_share_user,
                String.format(context.getString(R.string.dashboard_share_user, item.shareUser))
            )
            setText(
                R.id.tv_article_time,
                String.format(
                    context.getString(R.string.dashboard_share_time),
                    item.niceShareDate
                )
            )
        }

    companion object {
        const val VIEW_TYPE_ARTICLE = 0
    }
}