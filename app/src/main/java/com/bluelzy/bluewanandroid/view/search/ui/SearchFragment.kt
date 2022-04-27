package com.bluelzy.bluewanandroid.view.search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bluelzy.bluewanandroid.R
import com.bluelzy.bluewanandroid.base.BaseDataBindingFragment
import com.bluelzy.bluewanandroid.databinding.FragmentSearchBinding
import com.bluelzy.bluewanandroid.utils.whatIfNotNullOrEmpty
import com.bluelzy.bluewanandroid.view.detail.ui.GeneralActivity
import com.bluelzy.bluewanandroid.view.search.viewmodel.SearchViewModel
import com.bluelzy.bluewanandroid.widget.appbar.AppBarListener
import com.bluelzy.bluewanandroid.widget.appbar.AppbarController
import com.bluelzy.bluewanandroid.widget.tips.AppTipsController
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      11/26/2020
 *   @desc      搜索页
 */
class SearchFragment : BaseDataBindingFragment(), AppBarListener {

    private lateinit var binding: FragmentSearchBinding

    private lateinit var viewModel: SearchViewModel

    private lateinit var adapter: SearchDelegateAdapter

    private lateinit var tipsLayout: AppTipsController

    private var keyword: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding<FragmentSearchBinding>(inflater, R.layout.fragment_search, container)
        .apply {
            (activity as GeneralActivity).hideSpinner()
            lifecycleOwner = this@SearchFragment
            this@SearchFragment.binding = this
            viewModel = getViewModel<SearchViewModel>()
                .also { this@SearchFragment.viewModel = it }
            adapter = SearchDelegateAdapter().apply {
                loadMoreModule.setOnLoadMoreListener {
                    (viewModel as SearchViewModel).loadMoreByAuthor(keyword)
                }
            }.also { this@SearchFragment.adapter = it }
        }.root

    override fun initToolbar() {
        with(binding.layoutToolbar) {
            AppbarController.Builder()
                .init(activity, this)
                .setUseImageButton(true)
                .setOnSearchListener(this@SearchFragment)
                .build()
        }

        with(binding.idLayoutTips) {
            tipsLayout = AppTipsController(this,
                message = getString(R.string.search_default_hint),
                onRetry = {
                    tipsLayout.hideRetry()
                    viewModel.searchByAuthor(keyword)
                }
            )
            tipsLayout.show()
        }
    }

    override fun initViewModel() {
        viewModel.searchLiveData.observe(this, Observer { searchList ->
            (activity as GeneralActivity).hideSpinner()
            searchList.searchData?.dataList?.whatIfNotNullOrEmpty({
                if (it.size < 10) adapter.loadMoreModule.loadMoreEnd(true)
                tipsLayout.gone()
            }, {
                adapter.loadMoreModule.loadMoreEnd(true)
                if (viewModel.page == 0) {
                    tipsLayout.show()
                    tipsLayout.setNoResultText(keyword)
                }
            })
        })

        viewModel.toastLiveData.observe(this, Observer {
            tipsLayout.showRetry(getString(R.string.retry))
        })

        viewModel.showSpinnerLiveData.observe(this, Observer {
            if (it) {
                (activity as GeneralActivity).showSpinner()
            } else {
                (activity as GeneralActivity).hideSpinner()
            }
        })
    }

    override fun onSearchIconClick(keyword: String) {
        this.keyword = keyword
        (activity as GeneralActivity).showSpinner()
        if (adapter.itemCount > 0) {
            rv_search_list.removeAllViews()
            adapter.data = mutableListOf()
        }

        viewModel.searchByAuthor(keyword)
    }
}