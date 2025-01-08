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
    implementation(projects.modelShared.encryption.di)
    implementation(projects.modelShared.baseApiClient)
    implementation(projects.modelShared)
    implementation(projects.server.tgSms.domain)
    implementation(projects.server.tgSms.data)
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