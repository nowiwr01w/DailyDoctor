plugins {
    application
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlinJvm)
}

group = "nowiwr01p.daily.doctor"
version = "1.0.0"
application {
    mainClass.set("nowiwr01p.daily.doctor.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["development"] ?: "false"}")
}

dependencies {
    implementation(projects.shared)
    implementation(libs.bundles.server)
    implementation(libs.bundles.server.test)
}