package com.bluelzy.bluewanandroid.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bluelzy.bluewanandroid.R
import com.bluelzy.bluewanandroid.utils.ImageLoader.Companion.DEFAULT_CORNER
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import java.io.File

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      11/23/2020
 *   @desc      封装的图片加载管理器
 *
 *   目前一共有4种加载方式，分别是：
 *   1.从resource-id加载
 *   2.从Drawable加载
 *   3.从文件加载
 *   4.从url加载
 *
 *   自行封装图片加载管理器的好处是，目前使用的是Glide，万一以后需要换成其他的图片处理库，那么不需要改动到
 *   业务逻辑代码，只需要修改这一个类即可。
 */

/**
 *  使用接口[ImageLoader] 而不是使用**建造者模式**的原因是在Kotlin中可选参数更加方便，可省略get/set方法
 *  同时又能达到用户自行设置不同图片来源的目的。如果后续需要添加监听或者其他变换，可以用DSL的形式。
 *
 *  传统的Builder模式可以参考 {@see [com.bluelzy.bluewanandroid.widget.appbar.AppbarController]}
 */
interface ImageLoader {
    fun loadImage()

    companion object {
        const val DEFAULT_CORNER = 20
    }
}

/**
 * @param context      上下文
 * @param id           从res文件夹直接加载
 * @param url          从网络加载
 * @param drawable     Drawable类型资源
 * @param file         文件类型资源
 * @param isCircle     是不是圆形
 * @param isCorner     是不是圆角
 * @param view        设置图片的ImageView
 */
fun initImageLoader(
    context: Context,
    id: Int = 0,
    url: String? = null,
    drawable: Drawable? = null,
    file: File? = null,
    isCircle: Boolean = false,
    isCorner: Boolean = false,
    view: ImageView
): ImageLoader = when {
    id != 0 -> LoadImageFromRes(context, id, isCircle, isCorner, view)
    drawable != null -> LoadImageFromDrawable(context, drawable, isCircle, isCorner, view)
    file != null -> LoadImageFromFile(context, file, isCircle, isCorner, view)
    url != null -> LoadImageFromUrl(context, url, isCircle, isCorner, view)
    else -> LoadImageFromRes(context, R.drawable.ic_img_error, isCircle, isCorner, view)
}

/**
 * 使用[id]来加载图片资源到[view]中
 */
class LoadImageFromRes(
    val context: Context,
    private val id: Int,
    private val isCircle: Boolean,
    private val isCorner: Boolean,
    val view: ImageView
) : ImageLoader {
    override fun loadImage() {
        Glide.with(context)
            .load(id)
            .placeholder(R.drawable.ic_img_download)
            .error(R.drawable.ic_img_error)
            .centerCrop()
            .apply {
                if (isCircle) {
                    apply(RequestOptions.bitmapTransform(CircleCrop()))
                } else if (isCorner) {
                    apply(RequestOptions.bitmapTransform(RoundedCorners(DEFAULT_CORNER)))
                }
            }.also {
                it.into(view)
            }
    }
}

/**
 * 使用[drawable]加载图片资源到[view]中
 */
class LoadImageFromDrawable(
    val context: Context,
    private val drawable: Drawable,
    private val isCircle: Boolean,
    private val isCorner: Boolean,
    val view: ImageView
) : ImageLoader {
    override fun loadImage() {
        Glide.with(context)
            .load(drawable)
            .placeholder(R.drawable.ic_img_download)
            .error(R.drawable.ic_img_error)
            .apply {
                if (isCircle) {
                    apply(RequestOptions.bitmapTransform(CircleCrop()))
                } else if (isCorner) {
                    apply(RequestOptions.bitmapTransform(RoundedCorners(DEFAULT_CORNER)))
                }
            }.also {
                it.into(view)
            }
    }
}

/**
 * 使用[file]加载图片文件到[view]中
 */
class LoadImageFromFile(
    val context: Context,
    private val file: File,
    private val isCircle: Boolean,
    private val isCorner: Boolean,
    val view: ImageView
) : ImageLoader {
    override fun loadImage() {
        Glide.with(context)
            .load(file)
            .placeholder(R.drawable.ic_img_download)
            .error(R.drawable.ic_img_error)
            .apply {
                if (isCircle) {
                    apply(RequestOptions.bitmapTransform(CircleCrop()))
                } else if (isCorner) {
                    apply(RequestOptions.bitmapTransform(RoundedCorners(DEFAULT_CORNER)))
                }
            }.also {
                it.into(view)
            }
    }
}

/**
 * 使用[url]加载网络图片到[view]中
 */
class LoadImageFromUrl(
    val context: Context,
    private val url: String,
    private val isCircle: Boolean,
    private val isCorner: Boolean,
    val view: ImageView
) : ImageLoader {
    override fun loadImage() {
        Glide.with(context)
            .load(url)
            .placeholder(R.drawable.ic_img_download)
            .error(R.drawable.ic_img_error)
            .centerCrop()
            .apply {
                if (isCircle) {
                    apply(RequestOptions.bitmapTransform(CircleCrop()))
                } else if (isCorner) {
                    apply(RequestOptions.bitmapTransform(RoundedCorners(DEFAULT_CORNER)))
                }
            }.also {
                it.into(view)
            }
    }
}