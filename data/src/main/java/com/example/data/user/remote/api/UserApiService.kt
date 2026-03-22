package com.example.data.user.remote.api

import com.example.data.user.remote.model.UserDto

/**
 * Contract for user-related remote API operations.
 *
 * Defines how user data is fetched from a remote source.
 * This is a data-layer abstraction and may vary depending on API design.
 */
internal interface UserApiService {

    /**
     * Fetches the current user's profile.
     *
     * @param token Optional authorization token.
     * @return User data transfer object.
     */
    suspend fun getProfile(token: String?): UserDto
}
