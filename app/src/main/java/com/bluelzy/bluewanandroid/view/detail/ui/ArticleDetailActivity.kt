package com.bluelzy.bluewanandroid.view.detail.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.bluelzy.bluewanandroid.R
import com.bluelzy.bluewanandroid.base.BaseDataBindingActivity
import com.bluelzy.bluewanandroid.databinding.ActivityArticleDetailBinding
import com.bluelzy.bluewanandroid.extensions.applyMaterialTransform
import com.bluelzy.bluewanandroid.model.Article
import com.bluelzy.bluewanandroid.utils.whatIfNull

/**
 *   @author : BlueLzy
 *   @email  : bluehobert@gmail.com
 *   @date   : 2020/05/26
 *   @desc   : Article Content Page. Showing on a WebView.
 */
class ArticleDetailActivity : BaseDataBindingActivity() {

    private val binding: ActivityArticleDetailBinding by binding(R.layout.activity_article_detail)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.getStringExtra(KEY_ARTICLE_TITLE).whatIfNull { DEFAULT_TITLE }
            .let { title ->
                applyMaterialTransform(title)
                binding.apply {
                    this.title = title
                    this.activity = this@ArticleDetailActivity
                }
            }
        initWebView()
    }

    private var myWebChromeClient = object : WebChromeClient() {
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            if (newProgress == 100) {
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        with(binding.wvContent) {
            webChromeClient = myWebChromeClient
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
        }
        binding.wvContent.loadUrl(intent.getStringExtra(KEY_ARTICLE_LINK))
    }

    override fun onBackPressed() {
        if (binding.wvContent.canGoBack()) {
            binding.wvContent.goBack()
        } else {
            super.onBackPressed()
        }
    }

    companion object {

        private const val DEFAULT_TITLE = "Content"
        private const val KEY_ARTICLE_TITLE = "articleTitle"
        private const val KEY_ARTICLE_LINK = "articleLink"

        @JvmStatic
        fun newInstance(context: Context?, startView: View?, article: Article) {
            val intent = Intent(context, ArticleDetailActivity::class.java)
                .run {
                    putExtra(KEY_ARTICLE_TITLE, article.title)
                    putExtra(KEY_ARTICLE_LINK, article.link)
                }
            val options = ActivityOptions.makeSceneTransitionAnimation(
                context as Activity,
                startView,
                article.title
            )
            context.startActivity(intent, options.toBundle())
        }
    }
}