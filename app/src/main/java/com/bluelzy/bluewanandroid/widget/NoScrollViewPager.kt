package com.bluelzy.bluewanandroid.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

/**
 * @author    BlueLzy
 * @email     bluehobert@gmail.com
 * @date      2020/11/26
 * @desc      禁止滑动的ViewPager
 */
class NoScrollViewPager(context: Context, attrs: AttributeSet? = null) : ViewPager(context, attrs) {
    /**
     *  默认不能滑动
     */
    private var isCanScroll = false

    /**
     * 设置其是否能滑动换页
     *
     * @param isCanScroll false 不能换页， true 可以滑动换页
     */
    fun setScanScroll(isCanScroll: Boolean) {
        this.isCanScroll = isCanScroll
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean =
        isCanScroll && super.onInterceptTouchEvent(ev)

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent): Boolean = isCanScroll && super.onTouchEvent(ev)
}