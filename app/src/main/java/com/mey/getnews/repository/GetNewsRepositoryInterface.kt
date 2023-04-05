package com.mey.getnews.repository

import com.mey.getnews.data.model.Base
import com.mey.getnews.util.Resource

/**
created by Mehmet E. Yıldız
 **/

interface GetNewsRepositoryInterface {
    suspend fun getNews(
        query: String? = null,
        searchIn: String?,
        from: String?,
        to: String?,
        language: String?,
        sortBy: String?,
        page: Int,
        pageSize: Int,
        apiKey: String
    ): Resource<Base>

    suspend fun getHotNews(
        country: String?,
        category: String?,
        sources: String?,
        query: String?,
        page: Int,
        pageSize: Int,
        apiKey: String
    ): Resource<Base>
}