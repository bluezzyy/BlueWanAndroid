package com.bluelzy.bluewanandroid.view.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bluelzy.bluewanandroid.R
import com.bluelzy.bluewanandroid.adapter.HomeDelegateMultiAdapter
import com.bluelzy.bluewanandroid.base.BaseDataBindingFragment
import com.bluelzy.bluewanandroid.databinding.FragmentHomeBinding
import com.bluelzy.bluewanandroid.view.main.viewmodel.HomeViewModel
import org.koin.android.viewmodel.ext.android.getViewModel
import org.koin.core.KoinComponent

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      2020/4/8
 *   @desc
 */

class HomeFragment : BaseDataBindingFragment(), KoinComponent {

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = binding<FragmentHomeBinding>(inflater, R.layout.fragment_home, container)
        .apply {
            lifecycleOwner = this@HomeFragment
            viewModel = getViewModel<HomeViewModel>().apply {
                (activity as MainActivity).showSpinner()
                fetchArticles()
            }.also {
                this@HomeFragment.viewModel = it
            }
            adapter = HomeDelegateMultiAdapter().apply {
                loadMoreModule.setOnLoadMoreListener {
                    (viewModel as HomeViewModel).fetchArticles()
                }
            }
        }.root

    override fun initViewModel() {
        viewModel.articleLiveData.observe(this, Observer {
            (activity as MainActivity).hideSpinner()
        })
    }

}