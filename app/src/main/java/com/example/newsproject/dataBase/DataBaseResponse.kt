package com.example.newsproject.dataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dbResponse")

data class DataBaseResponse
    (
    val image :String,
    @PrimaryKey
    val title :String
)