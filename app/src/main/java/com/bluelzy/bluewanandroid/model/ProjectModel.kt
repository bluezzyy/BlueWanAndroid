package com.bluelzy.bluewanandroid.model

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      2020/8/17
 *   @desc
 */
class ProjectModel : ArrayList<ProjectModelItem>()

data class ProjectModelItem(
    val children: List<Any>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val visible: Int
)
