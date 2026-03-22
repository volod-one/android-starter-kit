package com.example.data.user.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Database entity representing a user.
 *
 * Defines the schema for the "users" table.
 * This model is specific to the data layer and may differ from
 * domain models depending on project needs.
 */
@Entity(tableName = "users")
data class UserEntity(

    /**
     * Unique identifier for the user.
     */
    @PrimaryKey(autoGenerate = true) val id: Int = 0,

    /**
     * User's display name.
     */
    val name: String,

    /**
     * User's email address.
     */
    val email: String,
)
