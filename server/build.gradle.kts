plugins {
    application
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.kotlinSerialization)
}

group = "nowiwr01p.daily.doctor.server.main"
version = "1.0.0"
application {
    mainClass.set("nowiwr01p.daily.doctor.server.main.ServerKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["development"] ?: "false"}")
}

dependencies {
    /**
     * SHARED MODELS
     */
    implementation(projects.modelShared)
    /**
     * SERVER
     */
    implementation(projects.server.di)
    implementation(projects.server.works)
    implementation(projects.server.token)
    implementation(projects.server.routes)
    /**
     * DATABASE
     */
    implementation(projects.database)
    implementation(projects.database.domain)
    /**
     * DEPENDENCIES
     */
    implementation(libs.bundles.database)
    implementation(libs.bundles.server)
    implementation(libs.bundles.server.test)
}