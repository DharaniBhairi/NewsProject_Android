package com.example.newsproject.utils

sealed class MyApiResponse<T>(val data: T? = null, val errorMessage: String? = null)
{
    class Loading<T> : MyApiResponse<T>()
    class Success<T>(data: T? = null) : MyApiResponse<T>(data = data)
    class Error<T>(errorMessage: String) : MyApiResponse<T>(errorMessage = errorMessage)

}