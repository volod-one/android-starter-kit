package com.example.core.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Factory for creating Room [RoomDatabase] instances.
 *
 * Provides a generic way to build databases without coupling core layer
 * to any specific schema or feature.
 */
class DatabaseFactory(private val context: Context) {

    /**
     * Creates a [RoomDatabase] of the given type.
     *
     * @param dbClass Database class definition.
     * @param dbName Name of the database file.
     */
    fun <T : RoomDatabase> create(
        dbClass: Class<T>,
        dbName: String
    ): T {
        return Room.databaseBuilder(
            context.applicationContext,
            dbClass,
            dbName
        ).fallbackToDestructiveMigration().build()
    }
}
