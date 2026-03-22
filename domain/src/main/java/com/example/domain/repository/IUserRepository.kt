package com.example.domain.repository

import com.example.domain.model.User
import kotlinx.coroutines.flow.Flow

/**
 * Repository contract defining user-related business operations.
 *
 * Specifies what data and actions are required by the domain layer,
 * without exposing implementation details.
 */
interface IUserRepository {

    /**
     * Returns the current user profile as a reactive stream.
     */
    fun getUserProfile(): Flow<User>

    /**
     * Requests a refresh of the user data.
     */
    suspend fun refreshUser()
}