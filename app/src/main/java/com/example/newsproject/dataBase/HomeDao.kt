package com.example.newsproject.dataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HomeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(dbResponse: DataBaseResponse)

    @Query("select * from dbResponse")
    suspend fun getData() : List<DataBaseResponse>

}