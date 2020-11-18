package com.bluelzy.bluewanandroid.view.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.bluelzy.bluewanandroid.R
import com.bluelzy.bluewanandroid.base.BaseDataBindingFragment
import com.bluelzy.bluewanandroid.databinding.FragmentKnowledgeBinding
import com.bluelzy.bluewanandroid.model.KnowledgeChildren
import com.bluelzy.bluewanandroid.model.KnowledgeData
import com.bluelzy.bluewanandroid.model.KnowledgeModel
import com.bluelzy.bluewanandroid.repository.SharedPreferencesRepository
import com.bluelzy.bluewanandroid.utils.LocalJsonUtils
import com.bluelzy.bluewanandroid.view.main.adapter.knowledge.KnowledgeDelegateMultiAdapter
import com.bluelzy.bluewanandroid.view.main.viewmodel.KnowledgeViewModel
import com.chad.library.adapter.base.entity.node.BaseNode
import com.google.gson.Gson
import kotlinx.android.synthetic.main.toolbar_home.view.*
import org.koin.android.viewmodel.ext.android.getViewModel

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      2020/4/8
 *   @desc      MainActivity -> KnowledgeFragment
 */

class KnowledgeFragment : BaseDataBindingFragment() {

    private lateinit var viewModel: KnowledgeViewModel
    private lateinit var binding: FragmentKnowledgeBinding
    private lateinit var adapter: KnowledgeDelegateMultiAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = binding<FragmentKnowledgeBinding>(inflater, R.layout.fragment_knowledge, container)
        .apply {
            lifecycleOwner = this@KnowledgeFragment
            this@KnowledgeFragment.binding = this

            viewModel = getViewModel<KnowledgeViewModel>()
                .also { this@KnowledgeFragment.viewModel = it }
            adapter = KnowledgeDelegateMultiAdapter()
                .also { this@KnowledgeFragment.adapter = it }
        }.root

    override fun initView() {
        binding.layoutToolbar.toolbar_title.text = getString(R.string.title_knowledge)
        binding.rvKnowledgeList.layoutManager = GridLayoutManager(context, FULL_WIDTH)
            .also { grid ->
                grid.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return when (adapter.data[position]) {
                            is KnowledgeData -> FULL_WIDTH
                            is KnowledgeChildren -> SINGLE_WIDTH
                            else -> FULL_WIDTH
                        }
                    }
                }
            }
    }

    override fun initViewModel() =
        viewModel.run {
            localKnowledgeData.observe(viewLifecycleOwner, Observer {
                val knowledgeModel = LocalJsonUtils.jsonToObject(
                    SharedPreferencesRepository.getKnowledgeJsonData(),
                    KnowledgeModel::class.java
                )
                adapter.run {
                    addData(knowledgeModel.knowledgeList as List<BaseNode>)
                }
            })

            knowledgeLiveData.observe(viewLifecycleOwner, Observer {
                SharedPreferencesRepository.setKnowledgeJsonData(Gson().toJson(it).toString())
                adapter.run {
                    if (!it.knowledgeList.isNullOrEmpty())
                        addData(it.knowledgeList as List<BaseNode>)
                }
            })
        }

    companion object {
        const val FULL_WIDTH = 3
        const val SINGLE_WIDTH = 1
    }


}