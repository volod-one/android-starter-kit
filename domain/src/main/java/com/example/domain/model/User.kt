package com.example.domain.model

/**
 * Domain model representing a user.
 *
 * Contains data used by the business layer.
 * This model is independent from data sources and platform-specific details.
 */
data class User(

    /**
     * Unique identifier of the user.
     */
    val id: Int,

    /**
     * User's display name.
     */
    val name: String,

    /**
     * User's email address.
     */
    val email: String,
)
