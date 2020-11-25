package com.bluelzy.bluewanandroid.view.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.bluelzy.bluewanandroid.R
import com.bluelzy.bluewanandroid.adapter.BannerViewAdapter
import com.bluelzy.bluewanandroid.base.BaseDataBindingFragment
import com.bluelzy.bluewanandroid.databinding.FragmentHomeBinding
import com.bluelzy.bluewanandroid.view.main.adapter.home.HomeDelegateMultiAdapter
import com.bluelzy.bluewanandroid.view.main.viewmodel.HomeViewModel
import org.koin.android.viewmodel.ext.android.getViewModel
import org.koin.core.KoinComponent

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      2020/4/8
 *   @desc
 */

class HomeFragment : BaseDataBindingFragment(), KoinComponent {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var viewModel: HomeViewModel

    private lateinit var adapter: HomeDelegateMultiAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding<FragmentHomeBinding>(inflater, R.layout.fragment_home, container)
        .apply {
            this@HomeFragment.binding = this
            lifecycleOwner = this@HomeFragment

            viewModel = getViewModel<HomeViewModel>().apply {
                (activity as MainActivity).showSpinner()
                fetchBanner()
                fetchArticles()
            }.also { this@HomeFragment.viewModel = it }

            adapter = HomeDelegateMultiAdapter().apply {
                loadMoreModule
                    .setOnLoadMoreListener { (viewModel as HomeViewModel).fetchArticles() }
                    .also { this@HomeFragment.adapter = this }
            }
        }.root

    override fun initViewModel() {
        viewModel.articleLiveData.observe(this, Observer {
            (activity as MainActivity).hideSpinner()
        })

        viewModel.bannerLiveData.observe(this, Observer {
            val view =
                LayoutInflater.from(context).inflate(R.layout.item_dashboard_banner, null)
            val viewPager = view.findViewById<ViewPager2>(R.id.vp_dashboard_banner)
            (viewPager as ViewPager2).adapter = BannerViewAdapter(it.banners)

            if (it.banners.isNotEmpty()) {
                adapter.addHeaderView(view, 0)
                binding.rvHomeList.scrollToPosition(0)
            }
        })
    }
}