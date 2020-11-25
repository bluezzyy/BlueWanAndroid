package com.bluelzy.bluewanandroid.model

import com.google.gson.annotations.SerializedName

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      11/25/2020
 *   @desc
 */
data class BannerModel(
    @SerializedName("data") val banners: List<BannerItem> = listOf(),
    val errorCode: Int = 0,
    val errorMsg: String = ""
)

data class BannerItem(
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String
)