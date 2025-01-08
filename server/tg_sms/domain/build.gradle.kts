plugins {
    application
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.kotlinSerialization)
}

dependencies {
    /**
     * SHARED MODELS
     */
    implementation(projects.modelShared)
    /**
     * KOIN
     */
    implementation(libs.koin)
    implementation(libs.koin.ktor)
    /**
     * KOTLIN
     */
    implementation(libs.coroutines)
    implementation(libs.kotlin.date.time)
    implementation(libs.kotlin.serialization.json)
    /**
     * BASE API CLIENT
     */
    implementation(projects.baseApiClient)
}