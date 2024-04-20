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
    implementation(projects.model)
    implementation(projects.server.domain)
    implementation(libs.bundles.server)
    implementation(libs.bundles.server.test)
}