package com.example.data.user.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.user.local.dao.UserDao
import com.example.data.user.local.entity.UserEntity

/**
 * Room database definition for user-related data.
 *
 * Contains DAOs and entities for the user feature.
 * This database is feature-specific and can be extended or modified
 * depending on application requirements.
 */
@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
internal abstract class UserDatabase : RoomDatabase() {

    /**
     * Provides access to user-related database operations.
     */
    abstract fun userDao(): UserDao
}
