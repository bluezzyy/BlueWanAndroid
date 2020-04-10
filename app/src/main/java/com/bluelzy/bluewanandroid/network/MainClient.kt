package com.bluelzy.bluewanandroid.network

import com.bluelzy.bluewanandroid.model.DashboardArticleModel

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      2020/4/9
 *   @desc
 */
class MainClient(private val blueWanAndroidService: BlueWanAndroidService) {

    fun fetchDashboardArticles(onResult: (response: ApiResponse<DashboardArticleModel>) -> Unit) {
        this.blueWanAndroidService.fetchDashboardArticles(0).transform(onResult)
    }

}