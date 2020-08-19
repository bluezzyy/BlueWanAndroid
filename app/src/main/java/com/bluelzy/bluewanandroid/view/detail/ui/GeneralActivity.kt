package com.bluelzy.bluewanandroid.view.detail.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.bluelzy.bluewanandroid.R
import com.bluelzy.bluewanandroid.base.BaseDataBindingActivity
import com.bluelzy.bluewanandroid.databinding.ActivityGeneralDetailBinding
import com.bluelzy.bluewanandroid.extensions.FragmentNavigation
import com.bluelzy.bluewanandroid.utils.whatIfNull
import com.bluelzy.bluewanandroid.view.category.ui.CategoryFragment

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      2020/8/16
 *   @desc
 */
class GeneralActivity : BaseDataBindingActivity() {

    private val binding by binding<ActivityGeneralDetailBinding>(R.layout.activity_general_detail)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val title = intent.getStringExtra(KEY_KNOWLEDGE_TITLE).whatIfNull { DEFAULT_TITLE }
        when (intent.getSerializableExtra(KEY_ACTIVITY_TYPE)) {
            ActivityType.ACTIVITY_KNOWLEDGE -> addCategoryFragment(title)
            ActivityType.ACTIVITY_PROJECT -> addProjectListFragment()
        }
    }

    private fun addCategoryFragment(title: String) {
        showSpinner()
        FragmentNavigation.addFragment(
            this,
            CategoryFragment(intent.getIntExtra(KEY_KNOWLEDGE_CID, 0), title)
        )
    }

    // TODO: 添加Project Item List Fragment
    private fun addProjectListFragment() {

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

    enum class ActivityType {
        ACTIVITY_KNOWLEDGE,
        ACTIVITY_PROJECT
    }

    companion object {

        private const val KEY_ACTIVITY_TYPE = "activityType"
        private const val DEFAULT_TITLE = "知识体系"
        private const val KEY_KNOWLEDGE_TITLE = "KnowledgeTitle"
        private const val KEY_KNOWLEDGE_CID = "knowledgeCid"

        @JvmStatic
        fun newInstance(
            activityType: ActivityType,
            context: Context?,
            cid: Int = 0,
            title: String = "WanAndroid"
        ) {
            val intent = Intent(context, GeneralActivity::class.java).run {
                putExtra(KEY_ACTIVITY_TYPE, activityType)
                putExtra(KEY_KNOWLEDGE_CID, cid)
                putExtra(KEY_KNOWLEDGE_TITLE, title)
            }

            context?.startActivity(intent)
        }
    }
}