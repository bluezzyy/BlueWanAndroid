package com.bluelzy.bluewanandroid.view.main.adapter.home

import androidx.core.text.HtmlCompat
import com.bluelzy.bluewanandroid.R
import com.bluelzy.bluewanandroid.model.Article
import com.bluelzy.bluewanandroid.utils.whatIfStringNotNullOrEmpty
import com.chad.library.adapter.base.BaseDelegateMultiAdapter
import com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 *   @author : BlueLzy
 *   @email  : bluehobert@gmail.com
 *   @date   : 2020/05/17
 *   @desc   : Dashboard Multi Item Types Adapter
 */
class HomeDelegateMultiAdapter : BaseDelegateMultiAdapter<Article, BaseViewHolder>(),
    LoadMoreModule {

    init {
        setMultiTypeDelegate(object : BaseMultiTypeDelegate<Article>() {
            override fun getItemType(data: List<Article>, position: Int): Int =
                VIEW_TYPE_ARTICLE
        })

        getMultiTypeDelegate()
            ?.addItemType(VIEW_TYPE_ARTICLE, R.layout.item_dashboard_article)
            .also { this.addChildClickViewIds(R.id.iv_favourite_article) }
    }

    override fun convert(holder: BaseViewHolder, item: Article) {
        when (holder.itemViewType) {
            VIEW_TYPE_ARTICLE -> setupArticleType(holder, item)
        }
    }

    private fun setupArticleType(holder: BaseViewHolder, item: Article) {
        with(holder) {
            setText(R.id.tv_article_title, HtmlCompat.fromHtml(item.title, 0))
            item.author.whatIfStringNotNullOrEmpty({ author ->
                setText(
                    R.id.tv_article_share_user,
                    String.format(context.getString(R.string.dashboard_author, author))
                )
                setText(
                    R.id.tv_article_time,
                    String.format(
                        context.getString(R.string.dashboard_share_time),
                        item.niceDate
                    )
                )
            }, {
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
            })
        }
    }

    companion object {
        const val VIEW_TYPE_ARTICLE = 0
    }
}