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
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent

class HomeFragment : BaseDataBindingFragment(), KoinComponent {

    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding<FragmentHomeBinding>(inflater, R.layout.fragment_home, container).apply {
            viewModel = homeViewModel
            lifecycleOwner = this@HomeFragment
            adapter = HomeDelegateMultiAdapter()
        }.root
    }

    override fun initData() {
        homeViewModel.fetchArticles()
    }

}