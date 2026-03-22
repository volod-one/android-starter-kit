package com.example.data.auth

import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.core.datastore.DataStoreFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * DataStore-based implementation of [TokenProvider].
 *
 * Stores the authentication token using Preferences DataStore.
 * This implementation is feature-specific (auth) and should be adapted
 * depending on project requirements (e.g., encryption, different storage, etc.).
 */
@Singleton
internal class AuthTokenStorage @Inject constructor(
    factory: DataStoreFactory,
) : TokenProvider {

    private val dataStore = factory.create("auth_prefs")

    companion object {

        private val AUTH_TOKEN_KEY = stringPreferencesKey("auth_token")
    }


    /**
     * Emits the current auth token from storage.
     */
    override val authToken: Flow<String?> = dataStore.data
        .map { preferences ->
            preferences[AUTH_TOKEN_KEY]
        }

    /**
     * Saves the auth token to storage.
     */
    override suspend fun saveToken(token: String) {
        dataStore.edit { preferences ->
            preferences[AUTH_TOKEN_KEY] = token
        }
    }

    /**
     * Removes the auth token from storage.
     */
    override suspend fun clearToken() {
        dataStore.edit { preferences ->
            preferences.remove(AUTH_TOKEN_KEY)
        }
    }
}
