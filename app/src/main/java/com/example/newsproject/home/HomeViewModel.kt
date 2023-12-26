package com.example.newsproject.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsproject.home.model.NewsResponse
import com.example.newsproject.utils.MyApiResponse
import kotlinx.coroutines.launch

class HomeViewModel() : ViewModel() {

    private val homeRepository = HomeRepository()
    var newsData: MutableLiveData<NewsResponse> = MutableLiveData()
    var newsLiveData: LiveData<NewsResponse> = newsData

    val newsResponseLiveData: LiveData<MyApiResponse<NewsResponse>> = homeRepository.responseLiveData

    fun getData(category: String) : LiveData<NewsResponse> {
        viewModelScope.launch {
            newsData.postValue(homeRepository.getNews(category).body())
            newsLiveData = newsData
            println("news=$newsData")
        }
        return newsData
    }
}