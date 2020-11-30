package com.bluelzy.bluewanandroid.widget.tips

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bluelzy.bluewanandroid.R

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      2020/11/30
 *   @desc      默认空页面 / 重试页面
 */
class AppTipsController constructor(
    private val view: View,
    private val picture: Int = 0,
    private val message: String? = null,
    onRetry: () -> Unit
) {

    private val ivPicture: ImageView
    private val tvMessage: TextView
    private val tvRetry: TextView

    init {
        view.run {
            ivPicture = findViewById(R.id.iv_tips_picture)
            tvMessage = findViewById(R.id.tv_tips_message)
            tvRetry = findViewById(R.id.tv_tips_retry)

            picture.takeIf { it != 0 }?.let { ivPicture.setImageResource(it) }
            message.takeIf { !it.isNullOrEmpty() }.let { tvMessage.text = it }
            tvRetry.setOnClickListener { onRetry() }
        }
    }

    fun show() {
        view.visibility = View.VISIBLE
    }

    fun gone() {
        view.visibility = View.GONE
    }

    fun setNoResultText(keyword: String) {
        tvMessage.text = String.format(view.context.getString(R.string.search_no_result), keyword)
    }

    fun showRetry(text: String) {
        tvRetry.visibility = View.VISIBLE
        tvRetry.text = text
    }

    fun hideRetry() {
        tvRetry.visibility = View.GONE
    }
}