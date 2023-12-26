package com.example.newsproject.dataBase

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DataBaseViewModel @Inject constructor(
    private val dataBaseRepository: DataBaseRepository
) :
    ViewModel() {

    /*     var homeDataBase = HomeDataBase()

    private var homeRepository = HomeRepository(homeDataBase)*/

    suspend fun insertData(dbResponse: DataBaseResponse){
        dataBaseRepository.insertData(dbResponse)
    }

    suspend fun getData(){
        dataBaseRepository.getSavedData()
    }

}