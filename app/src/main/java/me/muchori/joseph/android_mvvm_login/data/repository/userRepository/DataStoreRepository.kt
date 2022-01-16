package me.muchori.joseph.android_mvvm_login.data.repository.userRepository

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

    private val dataStore: DataStore<Preferences> = context.createDataStore(
        name = PREFERENCE_NAME
    )

    suspend fun splashScreen(key: String) {
        dataStore.edit { splash ->
            splash[PREF_IS_STARTED] = key
        }
    }


    val getPrefSplash: Flow<String> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.d("DataStore", exception.message.toString())
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val prefStarted = preferences[PREF_IS_STARTED] ?: "none"
            prefStarted
        }

    companion object {
        private val PREF_IS_STARTED = preferencesKey<String>(name = "PREF_IS_STARTED")

    }
}