package com.example.data.user.repository

import com.example.data.auth.TokenProvider
import com.example.data.user.mapper.toDomain
import com.example.data.user.mapper.toEntity
import com.example.data.user.local.dao.UserDao
import com.example.data.user.remote.api.UserApiService
import com.example.domain.model.User
import com.example.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Default implementation of [IUserRepository].
 *
 * Combines local and remote data sources and exposes user data to the domain layer.
 * Uses the database as a single source of truth and updates it via network requests.
 *
 * This implementation is feature-specific and should be adapted depending on
 * project requirements (e.g., caching strategy, error handling, sync logic).
 */
@Singleton
internal class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApiService,
    private val userDb: UserDao,
    private val tokenProvider: TokenProvider,
) : IUserRepository {

    /**
     * Returns the current user profile as a reactive stream.
     *
     * Data is always sourced from the local database.
     */
    override fun getUserProfile(): Flow<User> {
        // Always getting data from Database (Single Source of Truth)
        return userDb.getAllUsers().map { entities ->
            entities.firstOrNull()?.toDomain() ?: User(0, "Guest", "")
        }
    }

    /**
     * Fetches the latest user data from the remote source and stores it locally.
     */
    override suspend fun refreshUser() {
        val token = tokenProvider.authToken.first()
        val dto = userApi.getProfile(token)
        userDb.insertUser(dto.toEntity())
    }
}
