package com.example.core.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Default implementation of [DataStoreFactory].
 *
 * Uses Android Preferences DataStore to create instances stored
 * in app-specific files.
 */
@Singleton
internal class DataStoreFactoryImpl @Inject constructor(
    @param:ApplicationContext private val context: Context,
) : DataStoreFactory {

    /**
     * Creates a Preferences [DataStore] for the given name.
     */
    override fun create(name: String): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = { context.preferencesDataStoreFile(name) }
        )
    }
}
