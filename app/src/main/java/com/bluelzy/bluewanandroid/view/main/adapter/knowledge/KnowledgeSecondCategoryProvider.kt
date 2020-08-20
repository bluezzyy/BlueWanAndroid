package com.bluelzy.bluewanandroid.view.main.adapter.knowledge

import android.view.View
import com.bluelzy.bluewanandroid.R
import com.bluelzy.bluewanandroid.model.KnowledgeChildren
import com.bluelzy.bluewanandroid.view.detail.ui.GeneralActivity
import com.bluelzy.bluewanandroid.view.main.adapter.knowledge.KnowledgeDelegateMultiAdapter.Companion.VIEW_TYPE_SECOND_CATEGORY
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.provider.BaseNodeProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      2020/8/8
 *   @desc
 */
class KnowledgeSecondCategoryProvider : BaseNodeProvider() {

    override val itemViewType: Int
        get() = VIEW_TYPE_SECOND_CATEGORY

    override val layoutId: Int
        get() = R.layout.layout_item_second_knowledge

    override fun convert(helper: BaseViewHolder, item: BaseNode) {
        val entity = item as KnowledgeChildren
        helper.setText(R.id.tv_second_view, entity.name)
    }

    override fun onClick(helper: BaseViewHolder, view: View, data: BaseNode, position: Int) {
        super.onClick(helper, view, data, position)
        GeneralActivity.newInstance(GeneralActivity.ActivityType.ACTIVITY_KNOWLEDGE, context, (data as KnowledgeChildren).id, data.name)
    }
}