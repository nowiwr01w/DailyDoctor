plugins {
    application
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.kotlinSerialization)
}

group = "nowiwr01p.daily.doctor.server.tg_sms"
version = "1.0.0"
application {
    mainClass.set("nowiwr01p.daily.doctor.server.tg_sms.TgSmsMainKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["development"] ?: "false"}")
}

dependencies {
    /**
     * SHARED MODELS
     */
    implementation(projects.encryption)
    implementation(projects.modelShared.baseApiClient)
    implementation(projects.modelShared)
    implementation(projects.server.tgSms.di)
    implementation(projects.server.tgSms.domain)
    /**
     * KOIN
     */
    implementation(libs.koin)
    implementation(libs.koin.ktor)
    implementation(libs.koin.ktor.logger)
    implementation(libs.logback)
    /**
     * KOTLIN
     */
    implementation(libs.coroutines)
    implementation(libs.kotlin.date.time)
    implementation(libs.kotlin.serialization.json)
}