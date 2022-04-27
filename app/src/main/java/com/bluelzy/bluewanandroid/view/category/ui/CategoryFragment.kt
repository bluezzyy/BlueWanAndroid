package com.bluelzy.bluewanandroid.view.category.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bluelzy.bluewanandroid.R
import com.bluelzy.bluewanandroid.base.BaseDataBindingFragment
import com.bluelzy.bluewanandroid.databinding.FragmentCategoryBinding
import com.bluelzy.bluewanandroid.view.category.viewmodel.CategoryViewModel
import com.bluelzy.bluewanandroid.view.detail.ui.GeneralActivity
import com.bluelzy.bluewanandroid.view.main.adapter.home.HomeDelegateMultiAdapter
import com.bluelzy.bluewanandroid.widget.appbar.AppbarController

import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      2020/8/15
 *   @desc      KnowledgeFragment -> CategoryFragment
 */
class CategoryFragment(private val cid: Int, private val toolbarTitle: String) :
    BaseDataBindingFragment() {

    private lateinit var viewModel: CategoryViewModel
    private lateinit var binding: FragmentCategoryBinding
    private lateinit var adapter: HomeDelegateMultiAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding<FragmentCategoryBinding>(inflater, R.layout.fragment_category, container)
        .apply {
            lifecycleOwner = this@CategoryFragment
            this@CategoryFragment.binding = this
            viewModel = getViewModel<CategoryViewModel>()
                .also { this@CategoryFragment.viewModel = it }
            adapter = HomeDelegateMultiAdapter().apply {
                this@CategoryFragment.adapter = this
                loadMoreModule.setOnLoadMoreListener {
                    (viewModel as CategoryViewModel).fetchArticles(cid)
                }
            }
        }.root

    override fun initToolbar() {
        with(binding.layoutToolbar) {
            AppbarController.Builder()
                .init(activity, this)
                .setTitle(toolbarTitle)
                .build()
        }
    }

    override fun initViewModel() {
        viewModel.articleLiveData.observe(this, Observer {
            it.articleData?.articles?.let { articles ->
                if (articles.size < 3) adapter.loadMoreModule.loadMoreEnd(true)
            }

            it.articleData?.articles?.let { article ->
                adapter.addData(article)
                (activity as GeneralActivity).hideSpinner()
            }
        })
    }

    override fun initData() = viewModel.fetchArticles(cid)
}