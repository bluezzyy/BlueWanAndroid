package com.bluelzy.bluewanandroid.view.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bluelzy.bluewanandroid.R
import com.bluelzy.bluewanandroid.view.main.viewmodel.KnowledgeViewModel

class KnowledgeFragment : Fragment() {

    private lateinit var knowledgeViewModel: KnowledgeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        knowledgeViewModel =
                ViewModelProviders.of(this).get(KnowledgeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_knowledge, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        knowledgeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}