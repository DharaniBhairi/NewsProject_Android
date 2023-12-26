package com.example.newsproject.utils

import com.example.newsproject.home.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET(Constants.Companion.END_POINT)
    suspend fun getNewsData(
        @Query("apikey") key: String,
        @Query("category") query: String
    ) : Response<NewsResponse>
}