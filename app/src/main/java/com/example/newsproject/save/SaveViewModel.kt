package com.example.newsproject.save

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsproject.dataBase.DataBaseRepository
import com.example.newsproject.dataBase.DataBaseResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaveViewModel @Inject constructor(
    private val dataBaseRepository: DataBaseRepository
) :
    ViewModel() {

    var saveData: MutableLiveData<ArrayList<DataBaseResponse>> = MutableLiveData()
    var saveLiveData: LiveData<ArrayList<DataBaseResponse>> = saveData

    fun getData() {
        viewModelScope.launch {
            saveData.postValue(dataBaseRepository.getSavedData() as ArrayList<DataBaseResponse>?)
        }
    }
}