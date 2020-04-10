package com.bluelzy.bluewanandroid.view.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelzy.bluewanandroid.R
import com.bluelzy.bluewanandroid.base.BaseDataBindingFragment
import com.bluelzy.bluewanandroid.databinding.FragmentHomeBinding
import com.bluelzy.bluewanandroid.view.main.viewmodel.HomeViewModel
import org.koin.android.viewmodel.ext.android.getViewModel

class HomeFragment : BaseDataBindingFragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding<FragmentHomeBinding>(inflater, R.layout.fragment_home, container).apply {
            viewModel = getViewModel<HomeViewModel>()
            lifecycleOwner = this@HomeFragment
            binding = this
        }.root
    }

    override fun initView() {}

    override fun initData() {}


}