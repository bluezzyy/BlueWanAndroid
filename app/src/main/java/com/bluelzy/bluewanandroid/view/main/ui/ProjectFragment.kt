package com.bluelzy.bluewanandroid.view.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bluelzy.bluewanandroid.R
import com.bluelzy.bluewanandroid.base.BaseDataBindingFragment
import com.bluelzy.bluewanandroid.databinding.FragmentProjectBinding
import com.bluelzy.bluewanandroid.view.main.adapter.project.ProjectDelegateMultiAdapter
import com.bluelzy.bluewanandroid.view.main.viewmodel.ProjectViewModel
import kotlinx.android.synthetic.main.toolbar_home.view.*
import org.koin.android.viewmodel.ext.android.getViewModel

class ProjectFragment : BaseDataBindingFragment() {

    private lateinit var binding: FragmentProjectBinding
    private lateinit var viewModel: ProjectViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = binding<FragmentProjectBinding>(inflater, R.layout.fragment_project, container)
        .apply {
            lifecycleOwner = this@ProjectFragment
            this@ProjectFragment.binding = this

            viewModel = getViewModel<ProjectViewModel>()
                .apply {
                    (activity as MainActivity).showSpinner()
                    fetchProjectJson()
                }
                .also { this@ProjectFragment.viewModel = it }

            adapter = ProjectDelegateMultiAdapter()
        }.root


    override fun initView() {
        binding.layoutToolbar.toolbar_title.text = getString(R.string.title_project)
    }

    override fun initViewModel() {
        viewModel.projectLiveData.observe(this, Observer {
            (activity as MainActivity).hideSpinner()
        })
    }
}