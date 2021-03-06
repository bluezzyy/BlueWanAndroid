package com.bluelzy.bluewanandroid.network

import com.bluelzy.bluewanandroid.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      2020/4/9
 *   @desc
 */
interface BlueWanAndroidService {

    @GET("banner/json")
    fun fetchDashboardBanner(): Call<BannerModel>

    @GET("article/list/{page}/json")
    fun fetchDashboardArticles(@Path("page") page: Int): Call<DashboardArticleModel>

    @GET("tree/json")
    fun fetchKnowledgeJson(): Call<KnowledgeModel>

    @GET("article/list/{page}/json")
    fun fetchKnowledgeCategoryArticles(
        @Path("page") page: Int,
        @Query("cid") cid: Int
    ): Call<DashboardArticleModel>

    @GET("project/tree/json")
    fun fetchProjectJson(): Call<ProjectModel>

    @GET("project/list/{page}/json")
    fun fetchProjectList(@Path("page") page: Int, @Query("cid") cid: Int): Call<ProjectItemModel>

    @GET("article/list/{page}/json")
    fun searchByAuthor(@Path("page") page: Int, @Query("author") author: String)
        : Call<SearchAuthorModel>
}