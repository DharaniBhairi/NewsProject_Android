package com.example.newsproject.utils

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response

class ApiErrorHandler {

    private val  TAG = "ApiErrorHandler"
    suspend fun <T> safeApiCall(apiToBeCalled: suspend () -> Response<T>): MyApiResponse<T> {

        return withContext(Dispatchers.IO) {
            try {

                val response: Response<T> = apiToBeCalled()
                val responseCode = response.code()

                if (response.isSuccessful) {

                    MyApiResponse.Success(data = response.body()!!)
                } else {
                    when (responseCode) {
                        Constants.Companion.ApiErrorType.NOT_FOUND_ERROR.value -> {
                            MyApiResponse.Error(
                                errorMessage = "API Not Found"
                            )
                        }
                        Constants.Companion.ApiErrorType.UNAUTHORIZED_ERROR.value -> {
                            MyApiResponse.Error(
                                errorMessage = "Unauthorized User"
                            )
                        }
                        Constants.Companion.ApiErrorType.FORBIDDEN.value -> {
                            MyApiResponse.Error(
                                errorMessage = "There is an error.Please try again later"
                            )
                        }
                        Constants.Companion.ApiErrorType.INVALID_REQUEST.value -> {
                            MyApiResponse.Error(
                                errorMessage = "Invalid Api"
                            )
                        }
                        else -> {
                            MyApiResponse.Error(
                                errorMessage = "Internal server error"
                            )
                        }
                    }
                }

            } catch (e: HttpException) {
                Log.d(TAG , "HTTP Exception" + e.message())
                MyApiResponse.Error(errorMessage = e.message ?: "Http Error Occured")
            }  catch (e: Exception) {
                // Returning 'Something went wrong' in case
                // of unknown error wrapped in Resource.Error
                Log.d(TAG , "Exception" + e.message.toString())
                MyApiResponse.Error(errorMessage = "Something went wrong")
            }
        }
    }
}


