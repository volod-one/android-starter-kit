package com.example.core.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.cio.endpoint
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Factory for creating configured [HttpClient] instances.
 *
 * Provides a reusable HTTP client setup with JSON serialization,
 * logging, and default timeouts, without coupling to any API specifics.
 */
@Singleton
internal class KtorClientFactory @Inject constructor() {

    /**
     * Builds a configured [HttpClient].
     */
    fun build(): HttpClient {
        return HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }

            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }

            engine {
                endpoint {
                    connectTimeout = 5000
                    requestTimeout = 5000
                }
            }
        }
    }
}
