package com.bluelzy.bluewanandroid.view.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelzy.bluewanandroid.R
import com.bluelzy.bluewanandroid.base.BaseDataBindingFragment
import com.bluelzy.bluewanandroid.databinding.FragmentKnowledgeBinding
import com.bluelzy.bluewanandroid.view.main.viewmodel.KnowledgeViewModel
import kotlinx.android.synthetic.main.toolbar_home.view.*
import org.koin.android.viewmodel.ext.android.getViewModel

class KnowledgeFragment : BaseDataBindingFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = binding<FragmentKnowledgeBinding>(inflater, R.layout.fragment_knowledge, container)
        .apply {
            viewModel = getViewModel<KnowledgeViewModel>().apply { }
            lifecycleOwner = this@KnowledgeFragment
            this.layoutToolbar.toolbar_title.text = "知识体系"
        }.root
}