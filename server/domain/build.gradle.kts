plugins {
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.kotlinSerialization)
}

dependencies {
    implementation(projects.modelShared)
    implementation(projects.database)
    implementation(libs.bundles.database)
    implementation(libs.bundles.server)
}