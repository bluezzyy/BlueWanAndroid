package com.bluelzy.bluewanandroid.view.main.adapter.knowledge

import com.bluelzy.bluewanandroid.model.KnowledgeChildren
import com.bluelzy.bluewanandroid.model.KnowledgeData
import com.chad.library.adapter.base.BaseNodeAdapter
import com.chad.library.adapter.base.entity.node.BaseNode

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      2020/8/8
 *   @desc
 */
class KnowledgeDelegateMultiAdapter : BaseNodeAdapter() {

    init {
        addFullSpanNodeProvider(KnowledgeFirstCategoryProvider())
        addNodeProvider(KnowledgeSecondCategoryProvider())
    }

    override fun getItemType(data: List<BaseNode>, position: Int): Int {
        return when (data[position]) {
            is KnowledgeData -> VIEW_TYPE_FIRST_CATEGORY
            is KnowledgeChildren -> VIEW_TYPE_SECOND_CATEGORY
            else -> -1
        }
    }

    companion object {
        const val VIEW_TYPE_FIRST_CATEGORY = 0
        const val VIEW_TYPE_SECOND_CATEGORY = 1
    }

}