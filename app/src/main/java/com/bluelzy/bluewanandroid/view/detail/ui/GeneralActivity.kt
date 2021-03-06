package com.bluelzy.bluewanandroid.view.detail.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.annotation.NonNull
import com.bluelzy.bluewanandroid.R
import com.bluelzy.bluewanandroid.base.BaseDataBindingActivity
import com.bluelzy.bluewanandroid.databinding.ActivityGeneralDetailBinding
import com.bluelzy.bluewanandroid.extensions.FragmentNavigation
import com.bluelzy.bluewanandroid.utils.ViewUtil
import com.bluelzy.bluewanandroid.utils.whatIfNull
import com.bluelzy.bluewanandroid.view.category.ui.CategoryFragment
import com.bluelzy.bluewanandroid.view.projectlist.ui.ProjectListFragment
import com.bluelzy.bluewanandroid.view.search.ui.SearchFragment

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      2020/8/16
 *   @desc      通用的Activity
 */
class GeneralActivity : BaseDataBindingActivity() {

    private val binding by binding<ActivityGeneralDetailBinding>(R.layout.activity_general_detail)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val title = intent.getStringExtra(KEY_PAGE_TITLE).whatIfNull { DEFAULT_TITLE }
        val cid = intent.getIntExtra(KEY_PAGE_CID, 0)
        when (intent.getSerializableExtra(KEY_ACTIVITY_TYPE)) {
            ActivityType.ACTIVITY_KNOWLEDGE -> addCategoryFragment(cid, title)
            ActivityType.ACTIVITY_PROJECT -> addProjectListFragment(cid, title)
            ActivityType.ACTIVITY_SEARCH_AUTHOR -> addSearchFragment()
        }
    }

    private fun addCategoryFragment(cid: Int, title: String) {
        showSpinner()
        FragmentNavigation.addFragment(
            this,
            CategoryFragment(cid, title)
        )
    }

    private fun addProjectListFragment(cid: Int, title: String) {
        showSpinner()
        FragmentNavigation.addFragment(
            this,
            ProjectListFragment(cid, title)
        )
    }

    private fun addSearchFragment() {
        showSpinner()
        FragmentNavigation.addFragment(this, SearchFragment())
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    fun showSpinner() {
        binding.progressBar.visibility = View.VISIBLE
    }

    fun hideSpinner() {
        binding.progressBar.visibility = View.GONE
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            val currentView = currentFocus
            if (ViewUtil.isShouldHideSoftInput(currentView, ev)) {
                ViewUtil.hideSoftInput(currentView)
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    enum class ActivityType {
        ACTIVITY_KNOWLEDGE,
        ACTIVITY_PROJECT,
        ACTIVITY_SEARCH_AUTHOR,
    }

    companion object {
        private const val KEY_ACTIVITY_TYPE = "activityType"
        private const val DEFAULT_TITLE = "Blue玩安卓"
        private const val KEY_PAGE_TITLE = "PageTitle"
        private const val KEY_PAGE_CID = "PageCid"

        @JvmStatic
        fun newInstance(
            @NonNull activityType: ActivityType,
            context: Context?,
            cid: Int = 0,
            title: String = DEFAULT_TITLE
        ) {
            val intent = Intent(context, GeneralActivity::class.java).run {
                putExtra(KEY_ACTIVITY_TYPE, activityType)
                putExtra(KEY_PAGE_CID, cid)
                putExtra(KEY_PAGE_TITLE, title)
            }

            context?.startActivity(intent)
        }
    }
}