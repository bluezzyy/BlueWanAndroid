package com.bluelzy.bluewanandroid.adapter.knowledge

import android.view.View
import com.bluelzy.bluewanandroid.R
import com.bluelzy.bluewanandroid.adapter.KnowledgeDelegateMultiAdapter.Companion.VIEW_TYPE_FIRST_CATEGORY
import com.bluelzy.bluewanandroid.model.KnowledgeData
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.provider.BaseNodeProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      2020/8/8
 *   @desc
 */
class KnowledgeFirstCategoryProvider : BaseNodeProvider() {

    override val itemViewType: Int
        get() = VIEW_TYPE_FIRST_CATEGORY
    override val layoutId: Int
        get() = R.layout.layout_item_textview

    override fun convert(helper: BaseViewHolder, item: BaseNode) {
        val entity = item as KnowledgeData
        helper.setText(R.id.tv_content, entity.name)
    }

    override fun onClick(helper: BaseViewHolder, view: View, data: BaseNode, position: Int) {
        super.onClick(helper, view, data, position)
        (data as KnowledgeData).run {
            if (isExpanded) {
                getAdapter()?.collapse(position)
            } else {
                getAdapter()?.expand(position)
            }
        }
    }
}