package com.example.newsproject.topicSelection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsproject.topicSelection.model.MainResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TopicViewModel : ViewModel() {

    private  var repository = TopicSelectionRepository()
    var name = ""

    var checkedStateData : MutableLiveData<Int> = MutableLiveData()
    var checkedStateLiveData : LiveData<Int> = checkedStateData

    var categoryData : MutableLiveData<ArrayList<MainResponse>> = MutableLiveData()
    var categoryLiveData : LiveData<ArrayList<MainResponse>> = categoryData

    fun getCategory() {
        viewModelScope.launch {
            categoryData.postValue(repository.getCategoriesData())
            categoryLiveData = categoryData
            println("category=$categoryData")
        }
    }

    fun getUserName(){
        viewModelScope.launch(Dispatchers.IO) {

        }
    }
}