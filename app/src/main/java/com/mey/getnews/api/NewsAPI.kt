package com.mey.getnews.api

import com.mey.getnews.data.model.Base
import com.mey.getnews.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
created by Mehmet E. Yıldız
 **/
interface NewsAPI {

    @GET(Constants.EVERYTHING_END_POINT)
    suspend fun getNews(
        @Query("q") query: String?,
        @Query("searchIn") searchIn: String?,
        @Query("from") from: String?,
        @Query("to") to: String?,
        @Query("language") language: String?,
        @Query("sortBy") sortBy: String?,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("apiKey") apiKey: String
    ): Response<Base>

    @GET(Constants.TOP_HEADLINES_END_POINT)
    suspend fun getHotNews(
        @Query("country")   country: String?,
        @Query("category")  category: String?,
        @Query("sources")   sources: String?,
        @Query("q")         query: String?,
        @Query("page")      page: Int,
        @Query("pageSize")  pageSize: Int,
        @Query("apiKey")    apiKey: String,
    ): Response<Base>
}