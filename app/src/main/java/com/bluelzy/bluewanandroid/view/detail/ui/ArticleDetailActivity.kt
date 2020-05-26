package com.bluelzy.bluewanandroid.view.detail.ui

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.bluelzy.bluewanandroid.R
import com.bluelzy.bluewanandroid.base.BaseDataBindingActivity
import com.bluelzy.bluewanandroid.databinding.ActivityArticleDetailBinding
import com.bluelzy.bluewanandroid.extensions.applyMaterialTransform
import com.bluelzy.bluewanandroid.model.Article

/**
 *   @author : BlueLzy
 *   @email  : bluehobert@gmail.com
 *   @date   : 2020/05/26
 *   @desc   :
 */
class ArticleDetailActivity : BaseDataBindingActivity() {

    private val binding: ActivityArticleDetailBinding by binding(R.layout.activity_article_detail)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val title = intent.getStringExtra(KEY_ARTICLE_TITLE) ?: DEFAULT_TITLE
        applyMaterialTransform(title)
        binding.apply {
            this.title = title
        }
    }

    companion object {

        private const val DEFAULT_TITLE = "Content"
        private const val KEY_ARTICLE = "article"
        private const val KEY_ARTICLE_TITLE = "articleTitle"
        private const val KEY_ARTICLE_LINK = "articleLink"

        @JvmStatic
        fun newInstance(context: Context?, startView: View?, article: Article) {
            val intent = Intent(context, ArticleDetailActivity::class.java).run {
                putExtra(KEY_ARTICLE_TITLE, article.title)
                putExtra(KEY_ARTICLE_LINK, article.link)
            }
            val options =
                ActivityOptions.makeSceneTransitionAnimation(
                    context as Activity,
                    startView,
                    article.title
                )
            context.startActivity(intent, options.toBundle())
        }
    }
}