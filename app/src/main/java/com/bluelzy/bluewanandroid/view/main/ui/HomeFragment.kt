package com.bluelzy.bluewanandroid.view.main.ui

import android.os.Bundle
import android.os.Handler
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

    private lateinit var bannerAdapter: BannerViewAdapter

    private lateinit var viewPager: ViewPager2

    private val bannerHandler: Handler = Handler()
    private val task = object : Runnable {
        override fun run() {
            val page = viewPager.currentItem + 1
            viewPager.currentItem = page

            // Banner只会播放一次，到最后一个节点结束
            if (page != bannerAdapter.itemCount) {
                bannerHandler.postDelayed(this, BANNER_INTERVAL)
            } else {
                bannerHandler.removeCallbacks(this)
            }
        }
    }

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
            // Banner
            val view = LayoutInflater.from(context).inflate(R.layout.item_dashboard_banner, null)
            viewPager = view.findViewById(R.id.vp_dashboard_banner)
            viewPager.adapter = BannerViewAdapter(it.banners)
                .also { adapter -> bannerAdapter = adapter }
            if (it.banners.isNotEmpty()) {
                adapter.addHeaderView(view, 0)
                binding.rvHomeList.scrollToPosition(0)
                bannerHandler.postDelayed(task, BANNER_INTERVAL)
            }
        })
    }

    override fun onStop() {
        super.onStop()
        bannerHandler.removeCallbacks(task)
    }

    companion object {
        const val BANNER_INTERVAL = 6000L
    }
}