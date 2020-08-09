package com.bluelzy.bluewanandroid.view.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bluelzy.bluewanandroid.R
import com.bluelzy.bluewanandroid.base.BaseDataBindingFragment
import com.bluelzy.bluewanandroid.databinding.FragmentProjectBinding
import com.bluelzy.bluewanandroid.view.main.viewmodel.ProjectViewModel
import kotlinx.android.synthetic.main.toolbar_home.view.*
import org.koin.android.viewmodel.ext.android.getViewModel

class ProjectFragment : BaseDataBindingFragment() {

    override fun initViewModel() = Unit

    override fun initView() = Unit

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = binding<FragmentProjectBinding>(inflater, R.layout.fragment_project, container)
        .apply {
            viewModel = getViewModel<ProjectViewModel>().apply { }
            lifecycleOwner = this@ProjectFragment
            this.layoutToolbar.toolbar_title.text = "项目"
        }.root
}