package com.bluelzy.bluewanandroid.model

import com.google.gson.annotations.SerializedName

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      2020/8/19
 *   @desc
 */
data class ProjectItemModel(
    @SerializedName("data") val projectItem: ProjectItemData
)

data class ProjectItemData(
    val curPage: Int,
    @SerializedName("datas") val projects: List<ProjectItem>
)

data class ProjectItem(
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
    val tags: List<ProjectItemTag>,
    val title: String,
    val type: Int,
    val userId: Int,
    val visible: Int,
    val zan: Int
)

data class ProjectItemTag(
    val name: String,
    val url: String
)
