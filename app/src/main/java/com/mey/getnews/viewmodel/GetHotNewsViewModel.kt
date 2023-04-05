package com.mey.getnews.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mey.getnews.data.model.Base
import com.mey.getnews.repository.GetNewsRepository
import com.mey.getnews.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
created by Mehmet E. Yıldız
 **/
@HiltViewModel
class GetHotNewsViewModel @Inject constructor(
    private val repository: GetNewsRepository
) : ViewModel() {
    private var _hotNews = MutableLiveData<Resource<Base>>()
    val hotNews: LiveData<Resource<Base>> get() = _hotNews

    fun getHotNews(
        country: String?,
        category: String?,
        sources: String?,
        query: String?,
        page: Int,
        pageSize: Int,
        apiKey: String,
    ) {
        _hotNews.value = Resource.loading(null)
        viewModelScope.launch {
            val response =
                repository.getHotNews(
                    country = country,
                    category = category,
                    sources = sources,
                    query = query,
                    page = page,
                    pageSize = pageSize,
                    apiKey = apiKey
                )
            _hotNews.value = response
        }
    }

    fun clearNews() {
        _hotNews = MutableLiveData<Resource<Base>>()
    }
}