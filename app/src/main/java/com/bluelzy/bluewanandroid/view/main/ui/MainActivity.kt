package com.bluelzy.bluewanandroid.view.main.ui

import android.os.Bundle
import android.view.View
import com.bluelzy.bluewanandroid.R
import com.bluelzy.bluewanandroid.base.BaseDataBindingActivity
import com.bluelzy.bluewanandroid.databinding.ActivityMainBinding
import com.bluelzy.bluewanandroid.extensions.applyExitMaterialTransform
import com.bluelzy.bluewanandroid.view.main.adapter.MainPagerAdapter

class MainActivity : BaseDataBindingActivity() {

    private val binding: ActivityMainBinding by binding(R.layout.activity_main)

    override fun onCreate(savedInstanceState: Bundle?) {
        applyExitMaterialTransform()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding.apply {
            pagerAdapter = MainPagerAdapter(
                supportFragmentManager
            )
            navigation = mainBottomNavigation
        }
    }

    fun showSpinner() {
        binding.progressBar.visibility = View.VISIBLE
    }

    fun hideSpinner() {
        binding.progressBar.visibility = View.GONE
    }
}