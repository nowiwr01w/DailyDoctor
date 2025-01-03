plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.kotlinSerialization)
}

dependencies {
    implementation(projects.modelShared)
    implementation(projects.newResources)
    implementation(projects.database.domain)
    implementation(projects.database.tables)
    implementation(libs.bundles.database)
}