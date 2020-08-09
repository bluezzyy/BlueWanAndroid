package com.bluelzy.bluewanandroid.network

import com.bluelzy.bluewanandroid.model.DashboardArticleModel
import com.bluelzy.bluewanandroid.model.KnowledgeModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      2020/4/9
 *   @desc
 */
interface BlueWanAndroidService {

    @GET("article/list/{page}/json")
    fun fetchDashboardArticles(@Path("page") page: Int): Call<DashboardArticleModel>

    @GET("tree/json")
    fun fetchKnowledgeJson(): Call<KnowledgeModel>

}