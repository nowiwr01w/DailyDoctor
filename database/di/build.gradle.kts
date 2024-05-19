plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.kotlinSerialization)
}

dependencies {
    implementation(projects.modelShared)
    implementation(projects.database.domain)
    implementation(projects.database.data)
    implementation(libs.bundles.database)
}