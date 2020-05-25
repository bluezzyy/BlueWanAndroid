package com.bluelzy.bluewanandroid.binding

import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bluelzy.bluewanandroid.R
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 *   @author : BlueLzy
 *   @email  : bluehobert@gmail.com
 *   @date   : 2020/05/21
 *   @desc   :
 */

@BindingAdapter("pagerAdapter")
fun bindPagerAdapter(view: ViewPager, adapter: PagerAdapter) {
    view.adapter = adapter
    view.offscreenPageLimit = 3
}

@BindingAdapter("bindNavigation")
fun bindNavigation(view: ViewPager, navigationView: BottomNavigationView) {
    view.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) = Unit
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) = Unit

        override fun onPageSelected(position: Int) {
            navigationView.menu.getItem(position).isChecked = true
        }
    })

    navigationView.setOnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.navigation_home -> view.currentItem = 0
            R.id.navigation_dashboard -> view.currentItem = 1
            R.id.navigation_notifications -> view.currentItem = 2
        }
        true
    }
}