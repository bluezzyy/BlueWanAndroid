package com.bluelzy.bluewanandroid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bluelzy.bluewanandroid.R
import com.bluelzy.bluewanandroid.model.Article
import com.bluelzy.bluewanandroid.utils.whatIfNotNullAndEmpty
import com.bluelzy.bluewanandroid.view.detail.ui.ArticleDetailActivity
import kotlinx.android.synthetic.main.item_dashboard_article.view.*

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      2020/5/31
 *   @desc
 */
class HomePagingAdapter :
    PagedListAdapter<Article, RecyclerView.ViewHolder>(ARTICLE_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dashboard_article, parent, false)
        return HomePagingViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let { (holder as HomePagingViewHolder).bindArticle(it) }
    }

    class HomePagingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.tv_article_title
        private val tvDate: TextView = itemView.tv_article_time
        private val tvAuthor: TextView = itemView.tv_article_share_user

        fun bindArticle(article: Article) {
            with(article) {
                tvTitle.text = title
                author.whatIfNotNullAndEmpty({
                    tvDate.text = String.format("日期: %s", niceDate)
                    tvAuthor.text = String.format("作者: %s", author)
                }, {
                    tvDate.text = String.format("日期: %s", niceShareDate)
                    tvAuthor.text = String.format("分享人: %s", shareUser)
                })
            }

            itemView.setOnClickListener {
                ArticleDetailActivity.newInstance(
                    itemView.context,
                    itemView.findViewById(R.id.cl_item_article),
                    article
                )
            }
        }
    }

    companion object {
        val ARTICLE_COMPARATOR = object : DiffUtil.ItemCallback<Article>() {
            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean =
                oldItem.id == newItem.id

            override fun getChangePayload(oldItem: Article, newItem: Article): Any? {
                return null
            }
        }
    }
}