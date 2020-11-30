package com.bluelzy.bluewanandroid.widget.appbar

import android.text.Html
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.IntegerRes
import androidx.fragment.app.FragmentActivity
import com.bluelzy.bluewanandroid.R

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      2020/8/16
 *   @desc      顶部导航栏，使用Builder模式，
 *   另外可以参考{@see [com.bluelzy.bluewanandroid.utils.ImageLoader]的可选参数模式，更加简洁
 */
class AppbarController private constructor(
    private val activity: FragmentActivity?,
    view: View,
    private val title: String,
    private val backIcon: Int,
    private val searchIcon: Int,
    private val useImageButton: Boolean,
    private val listener: AppBarListener? = null
) {

    init {
        with(view) {
            val titleView = findViewById<TextView>(R.id.tv_toolbar_title)
            val backView = findViewById<ImageView>(R.id.iv_back)
            val searchImageButton = findViewById<ImageButton>(R.id.ib_search)
            val searchEditText = findViewById<EditText>(R.id.et_search_keyword)

            titleView.text = title

            backView.setOnClickListener {
                activity?.onBackPressed()
            }

            backIcon.takeIf { it != 0 }?.let { icon ->
                backView.setImageResource(icon)
            }

            searchIcon.takeIf { it != 0 }?.let { icon ->
                searchImageButton.setImageResource(icon)
            }

            if (useImageButton) {
                titleView.visibility = View.GONE
                searchEditText.visibility = View.VISIBLE
                searchImageButton.visibility = View.VISIBLE
                searchImageButton.setOnClickListener {
                    if (listener != null && searchEditText.text.isNotEmpty()) {
                        listener.onSearchIconClick(searchEditText.text.toString())
                    }
                }
            }
        }
    }

    class Builder {

        private var title: String = ""
        private var backIcon: Int = 0
        private var searchIcon: Int = 0
        private lateinit var view: View
        private var context: FragmentActivity? = null

        private var useImageButton: Boolean = false
        private var onSearchClick: AppBarListener? = null

        fun init(context: FragmentActivity?, view: View): Builder {
            this.context = context
            this.view = view
            return this
        }

        fun setTitle(title: String): Builder {
            this.title = Html.fromHtml(title).toString()
            return this
        }

        fun setBackIcon(@IntegerRes icon: Int): Builder {
            this.backIcon = icon
            return this
        }

        fun setSearchIcon(@IntegerRes icon: Int): Builder {
            this.searchIcon = icon
            return this
        }

        fun setUseImageButton(use: Boolean): Builder {
            useImageButton = use
            return this
        }

        fun setOnSearchListener(listener: AppBarListener): Builder {
            onSearchClick = listener
            return this
        }

        fun build(): AppbarController {
            return AppbarController(
                context,
                view,
                title,
                backIcon,
                searchIcon,
                useImageButton,
                onSearchClick
            )
        }
    }
}