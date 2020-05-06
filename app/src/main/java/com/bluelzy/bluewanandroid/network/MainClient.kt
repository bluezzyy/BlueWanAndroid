package com.bluelzy.bluewanandroid.network

import com.bluelzy.bluewanandroid.model.DashboardArticleModel

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      2020/4/9
 *   @desc
 */
class MainClient(private val blueWanAndroidService: BlueWanAndroidService) {

    fun fetchDashboardArticles(page: Int, onResult: (response: ApiResponse<DashboardArticleModel>) -> Unit) {
        this.blueWanAndroidService.fetchDashboardArticles(page).transform(onResult)
    }

}