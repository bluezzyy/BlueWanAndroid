package com.bluelzy.bluewanandroid.view.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelzy.bluewanandroid.R
import com.bluelzy.bluewanandroid.base.BaseDataBindingFragment
import com.bluelzy.bluewanandroid.databinding.FragmentProjectBinding
import com.bluelzy.bluewanandroid.view.main.viewmodel.ProjectViewModel
import com.bluelzy.bluewanandroid.widget.AppbarController
import org.koin.android.viewmodel.ext.android.getViewModel

class ProjectFragment : BaseDataBindingFragment() {

    private lateinit var binding: FragmentProjectBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = binding<FragmentProjectBinding>(inflater, R.layout.fragment_project, container)
        .apply {
            lifecycleOwner = this@ProjectFragment
            this@ProjectFragment.binding = this

            viewModel = getViewModel<ProjectViewModel>().apply { fetchProjectJson() }
        }.root

    override fun initToolbar() = with(binding.layoutToolbar) {
        AppbarController.Builder()
            .init(activity, this)
            .setTitle(context.getString(R.string.title_project))
            .build()
        Unit
    }


}