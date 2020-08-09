package com.bluelzy.bluewanandroid.model

import com.chad.library.adapter.base.entity.node.BaseExpandNode
import com.chad.library.adapter.base.entity.node.BaseNode
import com.google.gson.annotations.SerializedName

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      2020/8/2
 *   @desc      Model for Knowledge
 *              See : https://www.wanandroid.com/tree/json
 */

data class KnowledgeModel(
    @SerializedName("data") val knowledgeList: List<KnowledgeData>? = null
)

data class KnowledgeData(
    val children: List<KnowledgeChildren>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
) : BaseExpandNode() {
    init {
        isExpanded = false
    }

    override val childNode: MutableList<BaseNode>?
        get() = children.toMutableList()
}

data class KnowledgeChildren(
    val children: List<KnowledgeChildren>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
) : BaseExpandNode() {
    init {
        isExpanded = false
    }

    override val childNode: MutableList<BaseNode>?
        get() = null
}
