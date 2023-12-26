package com.example.newsproject.home

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsproject.home.model.NewsResponse
import com.example.newsproject.utils.ApiInstance
import com.example.newsproject.utils.Constants
import com.example.newsproject.utils.MyApiResponse
import retrofit2.Response

class HomeRepository() {

    private val responseData = MutableLiveData<MyApiResponse<NewsResponse>>()
    val responseLiveData: LiveData<MyApiResponse<NewsResponse>> = responseData
    private val callServices = ApiInstance.getRetroInstance()

    @SuppressLint("SuspiciousIndentation")
    suspend fun getNews(category: String) : Response<NewsResponse> {
        val data = callServices.getNewsData(Constants.API_KEY, category)

        if (data.isSuccessful && data.body() != null) {
            responseData.postValue(MyApiResponse.Success(data.body()!!))
        } else if (data.errorBody() != null) {
            responseData.postValue(MyApiResponse.Error("Something went wrong"))
        } else {
            responseData.postValue(MyApiResponse.Error("Something went wrong"))
        }

        return data
    }
}