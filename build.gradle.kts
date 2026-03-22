// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    // Core
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false

    // Compose (for Kotlin 2.x+)
    alias(libs.plugins.kotlin.compose) apply false

    // DI
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false

    // Serialization
    alias(libs.plugins.kotlin.serialization) apply false
}