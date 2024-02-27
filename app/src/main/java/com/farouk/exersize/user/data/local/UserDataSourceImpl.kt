package com.farouk.exersize.user.data.local

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.farouk.exersize.user.data.model.UserDataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

class UserDataSourceImpl(
    private val context: Context,
) : UserLocalDataSource {
    companion object {
        val LOGGED_IN_KEY = booleanPreferencesKey("is_logged_in")
        val USER_KEY = stringPreferencesKey("user")
        val USER_SETTINGS = "user_settings"
    }
    private val Context.dataStore by preferencesDataStore(
        name = USER_SETTINGS
    )
    override suspend fun setLoggedIn() {
           context.dataStore.edit {
                it[LOGGED_IN_KEY] = true
            }

    }

    override suspend fun isLoggedIn(): Flow<Boolean> {
        return context.dataStore.data.map {
            it[LOGGED_IN_KEY] ?: false
        }
    }

    override suspend fun saveData(userDataModel: UserDataModel) {

        context.dataStore.edit { preferences ->
            preferences[USER_KEY] = userDataModel.toString() // Convert UserDataModel to String
        }
    }


    override suspend fun getData(): Flow<UserDataModel?> {
        return context.dataStore.data.map { preferences ->
            val userDataJsonString = preferences[USER_KEY]
            userDataJsonString?.let {
                try {
                    Json.decodeFromString<UserDataModel>(it)
                } catch (e: Exception) {
                    // Handle the exception (e.g., log or return null)
                    null
                }
            }
        }
    }
}