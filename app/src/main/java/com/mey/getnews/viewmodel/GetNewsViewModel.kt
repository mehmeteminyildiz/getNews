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
class GetNewsViewModel
@Inject constructor(
    private val repository: GetNewsRepository
) : ViewModel() {

    private var _news = MutableLiveData<Resource<Base>>()
    val news: LiveData<Resource<Base>> get() = _news

    fun getNews(
        query: String?,
        searchIn: String?,
        from: String?,
        to: String?,
        language: String?,
        sortBy: String?,
        page: Int,
        pageSize: Int,
        apiKey: String
    ) {
        _news.value = Resource.loading(null)
        viewModelScope.launch {
            val response =
                repository.getNews(
                    query,
                    searchIn,
                    from,
                    to,
                    language,
                    sortBy,
                    page,
                    pageSize,
                    apiKey
                )
            _news.value = response
        }
    }

    fun clearNews() {
        _news = MutableLiveData<Resource<Base>>()
    }

}