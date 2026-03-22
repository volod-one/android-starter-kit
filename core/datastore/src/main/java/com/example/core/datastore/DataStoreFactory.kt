package com.example.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

/**
 * Factory for creating [DataStore] instances backed by Preferences.
 *
 * Provides a generic way to obtain DataStore without tying core layer
 * to specific keys or feature logic.
 */
interface DataStoreFactory {

    /**
     * Creates a [DataStore] with the given name.
     *
     * @param name File name used to store preferences.
     */
    fun create(name: String): DataStore<Preferences>
}