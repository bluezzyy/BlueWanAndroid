package com.bluelzy.bluewanandroid.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bluelzy.bluewanandroid.R
import com.bluelzy.bluewanandroid.base.BaseDataBindingFragment
import com.bluelzy.bluewanandroid.databinding.FragmentHomeBinding
import com.bluelzy.bluewanandroid.di.networkModule
import com.bluelzy.bluewanandroid.main.viewmodel.HomeViewModel
import com.bluelzy.bluewanandroid.network.RequestInterceptor
import org.koin.android.ext.android.getKoin
import org.koin.android.scope.currentScope
import org.koin.core.qualifier.named

class HomeFragment : BaseDataBindingFragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return binding<FragmentHomeBinding>(inflater, R.layout.fragment_home, container).apply {
        }.root
    }
}