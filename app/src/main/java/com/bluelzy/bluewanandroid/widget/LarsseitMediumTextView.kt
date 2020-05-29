package com.bluelzy.bluewanandroid.widget

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.bluelzy.bluewanandroid.widget.Font.Companion.LARSSEIT_MEDIUM

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      2020/5/29
 *   @desc
 */
class LarsseitMediumTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    init {
        if (!isInEditMode) {
            typeface = Typeface.createFromAsset(getContext().assets, LARSSEIT_MEDIUM)
        }
    }
}