plugins {
    application
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.kotlinSerialization)
}

dependencies {
    /**
     * PROJECT MODULES
     */
    implementation(projects.shared.baseApiClient)
    implementation(projects.shared)
    implementation(projects.server.tgSms.domain)
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
}