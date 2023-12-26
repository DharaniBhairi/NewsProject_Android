package com.example.newsproject.utils

class Constants {

    companion object{

        const val BASE_URL = "https://newsdata.io/api/1/"
        const val END_POINT = "news"
        const val API_KEY = "pub_17603478ac124717acf60040006023cb56222"
        const val DATA_STORE_NAME =  "user_name"
        const val USER_KEY = "USER_NAME"
        const val CATEGORIES_KEY = "CATEGORIES_SELECTED"
        const val STATE_KEY = "FRAGMENT_STATE"
        const val VALUE_KEY = "SELECTED_VALUE"

        enum class ApiErrorType(val value: Int) {
            NOT_FOUND_ERROR(404),
            UNAUTHORIZED_ERROR(401),
            FORBIDDEN(403),
            INVALID_REQUEST(400)
        }
    }
}