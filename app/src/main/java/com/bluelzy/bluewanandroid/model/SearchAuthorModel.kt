package com.bluelzy.bluewanandroid.model

import com.google.gson.annotations.SerializedName

/**
 * 按照作者搜索
 * The Model for the API: https://wanandroid.com/article/list/0/json?author=%E9%B8%BF%E6%B4%8B
 */
data class SearchAuthorModel(
    @SerializedName("data") val searchData: SearchData? = null
)

data class SearchData(
    val curPage: Int,
    @SerializedName("datas") val dataList: List<SearchAuthorItem>
)

data class SearchAuthorItem(
    val apkLink: String,
    val audit: Int,
    val author: String,
    val canEdit: Boolean,
    val chapterId: Int,
    val chapterName: String,
    val collect: Boolean,
    val courseId: Int,
    val desc: String,
    val descMd: String,
    val envelopePic: String,
    val fresh: Boolean,
    val id: Int,
    val link: String,
    val niceDate: String,
    val niceShareDate: String,
    val origin: String,
    val prefix: String,
    val projectLink: String,
    val publishTime: Long,
    val realSuperChapterId: Int,
    val selfVisible: Int,
    val shareDate: Long,
    val shareUser: String,
    val superChapterId: Int,
    val superChapterName: String,
    val tags: List<Tag>,
    val title: String,
    val type: Int,
    val userId: Int,
    val visible: Int,
    val zan: Int
)

data class Tag(
    val name: String,
    val url: String
)