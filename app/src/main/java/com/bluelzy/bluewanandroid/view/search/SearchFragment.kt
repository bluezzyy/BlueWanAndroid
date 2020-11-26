package com.bluelzy.bluewanandroid.view.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelzy.bluewanandroid.R
import com.bluelzy.bluewanandroid.base.BaseDataBindingFragment
import com.bluelzy.bluewanandroid.databinding.FragmentSearchBinding
import com.bluelzy.bluewanandroid.view.detail.ui.GeneralActivity
import com.bluelzy.bluewanandroid.widget.appbar.AppBarListener
import com.bluelzy.bluewanandroid.widget.appbar.AppbarController
import kotlinx.android.synthetic.main.toolbar_detail.view.*

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      11/26/2020
 *   @desc      搜索页
 */
class SearchFragment : BaseDataBindingFragment(), AppBarListener {

    private lateinit var binding: FragmentSearchBinding

    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding<FragmentSearchBinding>(inflater, R.layout.fragment_search, container)
        .apply {
            lifecycleOwner = this@SearchFragment
            this@SearchFragment.binding = this
            (activity as GeneralActivity).hideSpinner()
        }.root

    override fun initToolbar() {
        with(binding.layoutToolbar) {
            AppbarController.Builder()
                .init(activity, this)
                .setUseImageButton(true)
                .setOnSearchListener(this@SearchFragment)
                .build()
        }
    }

    override fun initView() {
        binding.layoutToolbar.et_search_keyword.requestFocus()
    }

    override fun onSearchIconClick(keyword: String) {
        // TODO：调用viewModel的接口
        Log.d("bluelzy", "search click")
    }
}