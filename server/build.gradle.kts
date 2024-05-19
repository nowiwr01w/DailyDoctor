plugins {
    application
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.kotlinSerialization)
}

group = "nowiwr01p.daily.doctor.server.main"
version = "1.0.0"
application {
    mainClass.set("nowiwr01p.daily.doctor.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["development"] ?: "false"}")
}

dependencies {
    implementation(projects.modelShared)
    implementation(projects.server.domain)
    implementation(projects.server.data) // TODO: This now how this should works
    implementation(projects.database)
    implementation(projects.database.di)
    implementation(projects.database.domain)
    implementation(libs.bundles.database)
    implementation(libs.bundles.server)
    implementation(libs.bundles.server.test)
}