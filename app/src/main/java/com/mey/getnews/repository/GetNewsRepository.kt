package com.mey.getnews.repository

import com.mey.getnews.api.NewsAPI
import com.mey.getnews.data.model.Base
import com.mey.getnews.util.Resource
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

/**
created by Mehmet E. Yıldız
 **/
class GetNewsRepository
@Inject constructor(
    private val retrofitApi: NewsAPI
) : GetNewsRepositoryInterface {

    override suspend fun getNews(
        query: String?,
        searchIn: String?,
        from: String?,
        to: String?,
        language: String?,
        sortBy: String?,
        page: Int,
        pageSize: Int,
        apiKey: String
    ): Resource<Base> {

        return try {
            val response =
                retrofitApi.getNews(query, searchIn, from, to, language, sortBy, page, pageSize, apiKey)
            if (response.isSuccessful) {
                Timber.e("response : $response")
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

    override suspend fun getHotNews(
        country: String?,
        category: String?,
        sources: String?,
        query: String?,
        page: Int,
        pageSize: Int,
        apiKey: String
    ): Resource<Base> {

        return try {
            val response =
                retrofitApi.getHotNews(country, category, sources, query, page, pageSize, apiKey)
            if (response.isSuccessful) {
                Timber.e("response : $response")
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