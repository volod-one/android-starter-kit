package com.example.data.user.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.user.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for user-related database operations.
 *
 * Defines queries for accessing and modifying [UserEntity] data.
 * This is feature-specific and should be adapted based on project requirements.
 */
@Dao
interface UserDao {

    /**
     * Returns all stored users as a reactive stream.
     */
    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<UserEntity>>

    /**
     * Inserts or replaces a user in the database.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)
}
