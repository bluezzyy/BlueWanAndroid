package com.bluelzy.bluewanandroid.view.main.adapter.project

import com.bluelzy.bluewanandroid.R
import com.bluelzy.bluewanandroid.model.ProjectItem
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseDelegateMultiAdapter
import com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      2020/8/20
 *   @desc
 */
class ProjectListDelegateAdapter : BaseDelegateMultiAdapter<ProjectItem, BaseViewHolder>(),
    LoadMoreModule {

    init {
        setMultiTypeDelegate(object : BaseMultiTypeDelegate<ProjectItem>() {
            override fun getItemType(data: List<ProjectItem>, position: Int): Int {
                return VIEW_TYPE_PROJECT
            }
        })

        getMultiTypeDelegate()?.addItemType(VIEW_TYPE_PROJECT, R.layout.item_project_item)
    }

    override fun convert(holder: BaseViewHolder, item: ProjectItem) {
        when (holder.itemViewType) {
            VIEW_TYPE_PROJECT -> setupProjectType(holder, item)
        }
    }

    private fun setupProjectType(holder: BaseViewHolder, item: ProjectItem) {
        with(holder) {
            setText(R.id.tv_project_item_name, item.title)
            setText(R.id.tv_project_item_desc, item.desc)
            Glide.with(context).load(item.envelopePic).into(getView(R.id.iv_project_item_image))
        }
    }

    companion object {
        const val VIEW_TYPE_PROJECT = 0
    }
}