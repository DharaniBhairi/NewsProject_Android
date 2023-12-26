package com.example.newsproject.utils

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.core.preferencesSetKey
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.first

class DataPreferences(context: Context) {

    private val dataStore = context.createDataStore(name = Constants.DATA_STORE_NAME)

    suspend fun storeUser( name: String,key:String) {
        val dataStoreKey= preferencesKey<String>(key)
        dataStore.edit {
            it[dataStoreKey]= name
        }
    }

    suspend fun storeCategories(category: Set<String>, key: String) {
        val dataStoreKey= preferencesSetKey<String>(key)
        dataStore.edit {
            it[dataStoreKey]= category
        }
    }

    suspend fun storeState( state :Boolean,key:String) {
        val dataStoreKey= preferencesKey<Boolean>(key)
        dataStore.edit {
            it[dataStoreKey]= state
        }
    }

    suspend fun storeValue( value : Int,key:String) {
        val dataStoreKey= preferencesKey<Int>(key)
        dataStore.edit {
            it[dataStoreKey]= value
        }
    }

    suspend fun readUser(key:String): String? {
        val dataStoreKey=preferencesKey<String>(key)
        val preferences=dataStore.data.first()
        return preferences[dataStoreKey]
    }

    suspend fun readCategories(key:String): Set<String>? {
        val dataStoreKey=preferencesSetKey<String>(key)
        val preferences=dataStore.data.first()
        return preferences[dataStoreKey]
    }

    suspend fun readState(key:String): Boolean? {
        val dataStoreKey=preferencesKey<Boolean>(key)
        val preferences=dataStore.data.first()
        return preferences[dataStoreKey]
    }

    suspend fun readValue(key:String): Int? {
        val dataStoreKey=preferencesKey<Int>(key)
        val preferences=dataStore.data.first()
        return preferences[dataStoreKey]
    }
}



