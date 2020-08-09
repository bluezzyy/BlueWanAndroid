/*
 * Designed and developed by 2020 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bluelzy.bluewanandroid.view.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.bluelzy.bluewanandroid.view.main.ui.HomeFragment
import com.bluelzy.bluewanandroid.view.main.ui.KnowledgeFragment
import com.bluelzy.bluewanandroid.view.main.ui.ProjectFragment
import timber.log.Timber

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      2020/5/25
 *   @desc
 */

class MainPagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> HomeFragment()
            1 -> KnowledgeFragment()
            2 -> ProjectFragment()
            else -> resetToHomeFragment(position)
        }

    override fun getCount() = 3

    private fun resetToHomeFragment(position: Int): HomeFragment {
        Timber.tag("BlueLzy").e("Fragment of $position is not found")
        return HomeFragment()
    }
}
