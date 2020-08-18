package com.bluelzy.bluewanandroid.widget

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.IntegerRes
import androidx.fragment.app.FragmentActivity
import com.bluelzy.bluewanandroid.R

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      2020/8/16
 *   @desc
 */
class AppbarController private constructor(
    private val activity: FragmentActivity?,
    view: View,
    private var title: String,
    private var backIcon: Int,
    private var searchIcon: Int
) {

    init {
        with(view) {
            val titleView = findViewById<TextView>(R.id.tv_toolbar_title)
            titleView.text = title

            val backView = findViewById<ImageView>(R.id.iv_back)
            backView.setOnClickListener {
                activity?.onBackPressed()
            }
        }
    }

     class Builder {

        private var title: String = ""
        private var backIcon: Int = 0
        private var searchIcon: Int = 0
        private lateinit var view: View
        private  var context: FragmentActivity? = null

        fun init(context: FragmentActivity?, view: View): Builder {
            this.context = context
            this.view = view
            return this
        }

        fun setTitle(title: String): Builder {
            this.title = title
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

        fun build(): AppbarController {
            return AppbarController(context, view, title, backIcon, searchIcon)
        }
    }
}