package com.bluelzy.bluewanandroid.view.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bluelzy.bluewanandroid.R
import com.bluelzy.bluewanandroid.adapter.HomePagingAdapter
import com.bluelzy.bluewanandroid.base.BaseDataBindingFragment
import com.bluelzy.bluewanandroid.databinding.FragmentHomeBinding
import com.bluelzy.bluewanandroid.view.main.viewmodel.HomeViewModel
import org.koin.android.viewmodel.ext.android.getViewModel
import org.koin.core.KoinComponent

class HomeFragment : BaseDataBindingFragment(), KoinComponent {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = binding<FragmentHomeBinding>(inflater, R.layout.fragment_home, container)
        .apply {
            lifecycleOwner = this@HomeFragment
            adapter = HomePagingAdapter()
            viewModel = getViewModel<HomeViewModel>().apply {
                postListData.observe(viewLifecycleOwner, Observer {
                    (adapter as HomePagingAdapter).submitList(it)
                })
            }
        }.root
}