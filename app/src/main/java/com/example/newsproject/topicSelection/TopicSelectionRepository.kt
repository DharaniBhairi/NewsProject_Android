package com.example.newsproject.topicSelection

import com.example.newsproject.topicSelection.model.CategoriesDataProvider
import com.example.newsproject.topicSelection.model.MainResponse

class TopicSelectionRepository {

    private var categoryDataProvider = CategoriesDataProvider()
    private val responseList = categoryDataProvider.categoriesData()

    fun getCategoriesData(): ArrayList<MainResponse> {
        return responseList
    }
}