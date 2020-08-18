package com.bluelzy.bluewanandroid.model

import com.google.gson.annotations.SerializedName

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      2020/8/17
 *   @desc
 */
data class ProjectModel(@SerializedName("data") val projectList: List<ProjectModelItem>? = null,
                   val errorCode: Int = 0,
                   val errorMsg: String? = null)


data class ProjectModelItem(
    val children: List<Any>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val visible: Int
)
