package com.example.newsproject.topicSelection.model

import com.example.newsproject.R

class CategoriesDataProvider {
    fun categoriesData () : ArrayList<MainResponse>{
        val list = ArrayList<MainResponse>()

        list.add(MainResponse(R.drawable.globe,"Business",false))
        list.add(MainResponse(R.drawable.animal,"environment",false))
        list.add(MainResponse(R.drawable.education,"food",false))
        list.add(MainResponse(R.drawable.games,"health",false))
        list.add(MainResponse(R.drawable.sports,"politics",false))
        list.add(MainResponse(R.drawable.globe,"science",false))
        list.add(MainResponse(R.drawable.animal,"sports",false))
        list.add(MainResponse(R.drawable.education,"Technology",false))
        list.add(MainResponse(R.drawable.games,"Top",false))

        return list
    }
}