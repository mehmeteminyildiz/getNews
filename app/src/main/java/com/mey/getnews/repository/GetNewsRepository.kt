package com.mey.getnews.repository

import com.mey.getnews.api.NewsAPI
import com.mey.getnews.data.model.NewsResponse
import com.mey.getnews.util.Resource
import retrofit2.Response
import javax.inject.Inject

/**
created by Mehmet E. Yıldız
 **/
class GetNewsRepository
@Inject constructor(
    private val retrofitApi: NewsAPI
) : GetNewsRepositoryInterface {
    override suspend fun getNews(
        query: String,
        searchIn: String,
        from: String,
        to: String,
        language: String,
        sortBy: String,
        page: Int,
        pageSize: Int,
        apiKey: String
    ): Resource<NewsResponse> {

        return try {
            val response =
                retrofitApi.getNews(query, searchIn, from, to, language, sortBy, page, pageSize, apiKey)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error", null)
            } else {
                Resource.error("Error", null)
            }
        } catch (e: Exception) {
            Resource.error("No Data!", null)
        }


    }


}