package com.example.newsproject.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [DataBaseResponse::class],
    version = 1,
    exportSchema = false
)
abstract class HomeDataBase : RoomDatabase() {
    abstract fun roomDao(): HomeDao
    companion object {
        @Volatile
        private var INSTANCE: HomeDataBase? = null
        private const val DB_NAME = "DB_NAME"

        fun getDatabase(context: Context): HomeDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HomeDataBase::class.java,
                    DB_NAME
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                instance
            }
        }
    }
}