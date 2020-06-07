package com.bluelzy.bluewanandroid.view.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelzy.bluewanandroid.R
import com.bluelzy.bluewanandroid.adapter.HomeDelegateMultiAdapter
import com.bluelzy.bluewanandroid.base.BaseDataBindingFragment
import com.bluelzy.bluewanandroid.databinding.FragmentHomeBinding
import com.bluelzy.bluewanandroid.view.main.viewmodel.HomeViewModel
import org.koin.android.viewmodel.ext.android.getViewModel
import org.koin.core.KoinComponent

class HomeFragment : BaseDataBindingFragment(), KoinComponent {

    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: HomeDelegateMultiAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = binding<FragmentHomeBinding>(inflater, R.layout.fragment_home, container)
        .apply {
            lifecycleOwner = this@HomeFragment
            viewModel = getViewModel<HomeViewModel>().apply {
                this@HomeFragment.viewModel = this
                fetchArticles()
            }
            adapter = HomeDelegateMultiAdapter().apply {
                this@HomeFragment.adapter = this
                loadMoreModule.setOnLoadMoreListener {
                    (viewModel as HomeViewModel).fetchArticles()
                }
            }
        }.root

}