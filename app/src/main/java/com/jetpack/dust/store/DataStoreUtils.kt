package com.jetpack.dust.store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

import com.blankj.utilcode.util.Utils
import com.jetpack.dust.SettingPreferences

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*



val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")



val Context.settingsDataStore: DataStore<SettingPreferences> by dataStore(
    fileName = "settings.pb",
    serializer = SettingBeanSerializer
)


object StoreUtils {

    private val appContext: Context by lazy { Utils.getApp() }

    val KEY_COUNTER = intPreferencesKey("example_counter")


    //sava
     fun saveNumber(value: Int){

        runBlocking {
            appContext.dataStore.edit {
                it[KEY_COUNTER]=value
            }
        }

    }

    //get
    suspend fun getNumber(){

        appContext.dataStore.data.map { preferences->
            preferences[KEY_COUNTER]?:0
        }.flowOn(Dispatchers.Main).collect()
    }

    suspend fun  saveBean(){
        withContext(Dispatchers.IO) {
            val appContext = Utils.getApp()
            appContext.settingsDataStore.updateData { currentSettings ->
                currentSettings.toBuilder()
                        //保存各种数据
                    .setExampleCounter(currentSettings.exampleCounter + 1)
                    .setQuery("1000000")
                    .build()
            }
        }

    }

     fun  getBean(): SettingPreferences {
        val aa= runBlocking {
             val appContext = Utils.getApp()
            appContext.settingsDataStore.data.first()
//             appContext.settingsDataStore.data.map {
//                 it.exampleCounter
//             }.collect()
            //直接获取数据
//            appContext.settingsDataStore.data.collect{
//
//            }
         }
         return aa
    }

}

