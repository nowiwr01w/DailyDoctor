plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.kotlinSerialization)
}

dependencies {
    implementation(projects.modelShared)
    implementation(projects.server.database.domain)
    implementation(projects.server.database.tables)
    implementation(libs.bundles.database)
}