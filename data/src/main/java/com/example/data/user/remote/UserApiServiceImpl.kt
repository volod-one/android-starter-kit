package com.example.data.user.remote

import com.example.data.user.remote.api.UserApiService
import com.example.data.user.remote.model.UserDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import javax.inject.Inject

/**
 * Default implementation of [UserApiService] using Ktor [HttpClient].
 *
 * Handles network requests and maps responses to [UserDto].
 * This implementation is feature-specific and should be adapted
 * based on API requirements (e.g., endpoints, headers, error handling).
 */
internal class UserApiServiceImpl @Inject constructor(
    private val client: HttpClient,
) : UserApiService {

    /**
     * Retrieves the current user's profile from the remote API.
     */
    override suspend fun getProfile(token: String?): UserDto {
        return client.get("https://api.example.com/v1/profile") {
            header("Authorization", "Bearer $token")
        }.body()
    }
}
