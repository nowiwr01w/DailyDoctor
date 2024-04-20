plugins {
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.kotlinSerialization)
}

dependencies {
    implementation(projects.model)
    implementation(projects.server.domain)
    implementation(libs.bundles.server)
}