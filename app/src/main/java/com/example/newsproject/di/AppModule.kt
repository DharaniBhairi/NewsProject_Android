package com.example.newsproject.di

import android.content.Context
import com.example.newsproject.dataBase.HomeDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideDatabaseHelper(@ApplicationContext context: Context): HomeDataBase{
        return HomeDataBase.getDatabase(context)
    }

}