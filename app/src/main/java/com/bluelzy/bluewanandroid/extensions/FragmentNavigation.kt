package com.bluelzy.bluewanandroid.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.bluelzy.bluewanandroid.R

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      2020/8/15
 *   @desc
 */

object FragmentNavigation {

    fun replaceFragment(context: Fragment, fragment: Fragment) {
        context.fragmentManager?.beginTransaction()
            ?.addToBackStack(fragment::class.java.simpleName)
            ?.replace(R.id.fc_container_view, fragment)
            ?.commit()
    }

    fun addFragment(context: FragmentActivity, fragment: Fragment) {
        context.supportFragmentManager.beginTransaction()
            .add(R.id.fc_container_view, fragment)
            .addToBackStack(fragment::class.java.simpleName)
            .commitAllowingStateLoss()
    }
}