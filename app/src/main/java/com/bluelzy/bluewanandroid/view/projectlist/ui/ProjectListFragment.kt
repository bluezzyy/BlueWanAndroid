package com.bluelzy.bluewanandroid.view.projectlist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bluelzy.bluewanandroid.R
import com.bluelzy.bluewanandroid.base.BaseDataBindingFragment
import com.bluelzy.bluewanandroid.databinding.FragmentProjectListBinding
import com.bluelzy.bluewanandroid.view.detail.ui.GeneralActivity
import com.bluelzy.bluewanandroid.view.main.adapter.project.ProjectListDelegateAdapter
import com.bluelzy.bluewanandroid.view.projectlist.viewmodel.ProjectListViewModel
import com.bluelzy.bluewanandroid.widget.appbar.AppbarController
import org.koin.android.viewmodel.ext.android.getViewModel

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      2020/8/20
 *   @desc      项目列表页
 */
class ProjectListFragment(private val cid: Int, private val toolbarTitle: String) :
    BaseDataBindingFragment() {

    private lateinit var viewModel: ProjectListViewModel
    private lateinit var binding: FragmentProjectListBinding
    private lateinit var adapter: ProjectListDelegateAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        binding<FragmentProjectListBinding>(inflater, R.layout.fragment_project_list, container)
            .apply {
                lifecycleOwner = this@ProjectListFragment
                this@ProjectListFragment.binding = this

                viewModel = getViewModel<ProjectListViewModel>()
                    .apply {
                        (activity as GeneralActivity).showSpinner()
                        fetchProjects(cid)
                    }
                    .also { this@ProjectListFragment.viewModel = it }

                adapter = ProjectListDelegateAdapter().apply {
                    this@ProjectListFragment.adapter = this
                    loadMoreModule.setOnLoadMoreListener {
                        (viewModel as ProjectListViewModel).fetchProjects(cid)
                    }
                }
            }.root

    override fun initToolbar() {
        with(binding.layoutToolbar) {
            AppbarController.Builder()
                .init(activity, this)
                .setTitle(toolbarTitle)
                .build()
        }
    }

    override fun initViewModel() {
        viewModel.projectListLiveData.observe(this, Observer {
            it.projectItem?.projects?.let {projects ->
                if (projects.size <= 3) adapter.loadMoreModule.loadMoreEnd(true)
            }
            (activity as GeneralActivity).hideSpinner()
        })
    }
}