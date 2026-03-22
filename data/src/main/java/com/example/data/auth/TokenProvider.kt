package com.example.data.auth

import kotlinx.coroutines.flow.Flow

/**
 * Contract for providing and managing an authentication token.
 *
 * This is a feature-specific abstraction used by the data layer (e.g., for API authorization).
 * Its implementation and behavior may vary between projects, so treat this as a reference
 * rather than a fixed solution.
 */
internal interface TokenProvider {

    /**
     * Stream of the current auth token.
     */
    val authToken: Flow<String?>

    /**
     * Persists the given auth token.
     */
    suspend fun saveToken(token: String)

    /**
     * Clears the stored auth token.
     */
    suspend fun clearToken()
}
