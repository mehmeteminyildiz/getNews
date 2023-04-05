package com.mey.getnews.repository

import com.mey.getnews.data.model.NewsResponse
import com.mey.getnews.util.Resource
import retrofit2.Response

/**
created by Mehmet E. Yıldız
 **/

interface GetNewsRepositoryInterface {
    suspend fun getNews(
        query: String,
        searchIn: String,
        from: String,
        to: String,
        language: String,
        sortBy: String,
        page: Int,
        pageSize: Int,
        apiKey: String
    )  : Resource<NewsResponse>
}