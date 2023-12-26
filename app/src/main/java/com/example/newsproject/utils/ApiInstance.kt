package com.example.newsproject.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiInstance {
    companion object {

        fun getRetroInstance(): ApiServices {
            return Retrofit.Builder()
                .baseUrl(Constants.Companion.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiServices::class.java)
        }
    }
}