package me.muchori.joseph.android_mvvm_login.repository.userRepository

import android.content.Context
import android.util.Log
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

const val PREFERENCE_NAME = "my_preferences"

class DataStoreRepository(context: Context) {

//    private object PreferencesKeys{
//        val id = preferencesKey<String>("id")
//    }


    private val dataStore: DataStore<Preferences> = context.createDataStore(
        name = PREFERENCE_NAME
    )

    val refreshToken: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[KEY_REFRESH_TOKEN]
        }

    suspend fun saveRefreshToken(refresh_token: String){
        dataStore.edit { refreshToken ->
            refreshToken[KEY_REFRESH_TOKEN] = refresh_token
        }
    }

    suspend fun saveToDataStore(id: String) {
        dataStore.edit { preferences ->
            preferences[KEY_ID] = id
        }
    }

    val readIdFromDataStore: Flow<String> = dataStore.data
        .catch { exception ->
            if (exception is IOException){
                Log.d("DataStore", exception.message.toString())
                emit(emptyPreferences())
            } else{
                throw exception
            }
        }
        .map { preferences ->
            val my_id = preferences[KEY_ID] ?: "none"
            my_id
        }

    companion object{
        private val KEY_ID = preferencesKey<String>( name = "id")
        private val KEY_REFRESH_TOKEN = preferencesKey<String>( name = "refresh_token")
    }
}