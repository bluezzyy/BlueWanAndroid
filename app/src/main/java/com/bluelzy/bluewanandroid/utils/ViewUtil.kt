package com.bluelzy.bluewanandroid.utils

import android.content.Context
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      2020/11/29
 *   @desc
 */
object ViewUtil {

    /**
     * 判断是不是在EditText范围内，不是的话需要返回true，隐藏软键盘
     */
    fun isShouldHideSoftInput(view: View?, event: MotionEvent): Boolean {
        if (view != null && view is EditText) {
            val left = event.rawX
            val top = event.rawY
            val right = left + view.width
            val bottom = top + view.height
            return !(event.rawX > left && event.rawX < right && event.rawY > top && event.rawY < bottom)
        }
        return false
    }

    /**
     * 隐藏软键盘
     */
    fun hideSoftInput(view: View?) {
        view?.let { v ->
            val im = v.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            im?.let {
                im.hideSoftInputFromWindow(v.windowToken, 0)
            }
        }
    }
}