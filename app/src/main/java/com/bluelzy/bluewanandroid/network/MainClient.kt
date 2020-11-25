package com.bluelzy.bluewanandroid.network

import com.bluelzy.bluewanandroid.model.*

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      2020/4/9
 *   @desc
 */
class MainClient(private val blueWanAndroidService: BlueWanAndroidService) {

    fun fetchDashboardBanner(onResult: (response: ApiResponse<BannerModel>) -> Unit) {
        this.blueWanAndroidService.fetchDashboardBanner().transform(onResult)
    }

    fun fetchDashboardArticles(
        page: Int,
        onResult: (response: ApiResponse<DashboardArticleModel>) -> Unit
    ) {
        this.blueWanAndroidService.fetchDashboardArticles(page).transform(onResult)
    }

    fun fetchKnowledgeJson(onResult: (response: ApiResponse<KnowledgeModel>) -> Unit) {
        this.blueWanAndroidService.fetchKnowledgeJson().transform(onResult)
    }

    fun fetchKnowledgeCategoryArticles(
        cid: Int,
        page: Int,
        onResult: (response: ApiResponse<DashboardArticleModel>) -> Unit
    ) {
        this.blueWanAndroidService.fetchKnowledgeCategoryArticles(page, cid).transform(onResult)
    }

    fun fetchProjectJson(onResult: (response: ApiResponse<ProjectModel>) -> Unit) {
        this.blueWanAndroidService.fetchProjectJson().transform(onResult)
    }

    fun fetchProjectList(
        page: Int,
        cid: Int,
        onResult: (response: ApiResponse<ProjectItemModel>) -> Unit
    ) {
        this.blueWanAndroidService.fetchProjectList(page, cid).transform(onResult)
    }

}