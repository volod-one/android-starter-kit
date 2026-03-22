package com.example.data.user.remote.model

import kotlinx.serialization.Serializable

/**
 * Data Transfer Object representing user data from a remote source.
 *
 * Used for serialization/deserialization of API responses.
 * This model reflects API structure and may differ from local or domain models.
 */
@Serializable
data class UserDto(

    /**
     * Unique identifier of the user.
     */
    val id: Int,

    /**
     * Username provided by the API.
     */
    val username: String,

    /**
     * User's email address.
     */
    val email: String
)