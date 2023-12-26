package com.example.newsproject.dataBase

import javax.inject.Inject

class DataBaseRepository @Inject constructor(
    private val homeDataBase : HomeDataBase
)
{
    private val homeDao : HomeDao? = null
    lateinit var homeData: List<DataBaseResponse>

    suspend fun insertData(dataBaseResponse: DataBaseResponse){
        return homeDataBase.roomDao().insertData(dataBaseResponse)
    }

    suspend fun getSavedData() : List<DataBaseResponse>
    {
        return homeDataBase.roomDao().getData()
    }
}