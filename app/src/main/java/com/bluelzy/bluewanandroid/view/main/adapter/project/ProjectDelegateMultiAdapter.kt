package com.bluelzy.bluewanandroid.view.main.adapter.project

import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_COMPACT
import com.bluelzy.bluewanandroid.R
import com.bluelzy.bluewanandroid.model.ProjectModelItem
import com.chad.library.adapter.base.BaseDelegateMultiAdapter
import com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      2020/8/18
 *   @desc
 */
class ProjectDelegateMultiAdapter : BaseDelegateMultiAdapter<ProjectModelItem, BaseViewHolder>() {

    init {
        setMultiTypeDelegate(object : BaseMultiTypeDelegate<ProjectModelItem>() {
            override fun getItemType(data: List<ProjectModelItem>, position: Int): Int {
                return VIEW_TYPE_PROJECT
            }
        })

        getMultiTypeDelegate()?.addItemType(VIEW_TYPE_PROJECT, R.layout.item_dashboard_project)
    }

    override fun convert(holder: BaseViewHolder, item: ProjectModelItem) {
        when (holder.itemViewType) {
            VIEW_TYPE_PROJECT -> setupProjectType(holder, item)
        }
    }

    private fun setupProjectType(holder: BaseViewHolder, item: ProjectModelItem) {
        with(holder) {
            setText(R.id.tv_project_name, HtmlCompat.fromHtml(item.name, FROM_HTML_MODE_COMPACT))
            setBackgroundResource(
                R.id.iv_project_logo,
                BACKGROUND_ARRAY[holder.adapterPosition % BACKGROUND_ARRAY.size]
            )
        }
    }

    companion object {
        const val VIEW_TYPE_PROJECT = 0
        val BACKGROUND_ARRAY = intArrayOf(
            R.drawable.ic_img_project_bg1,
            R.drawable.ic_img_project_bg2,
            R.drawable.ic_img_project_bg3,
            R.drawable.ic_img_project_bg4,
            R.drawable.ic_img_project_bg5,
            R.drawable.ic_img_project_bg6
        )
    }
}