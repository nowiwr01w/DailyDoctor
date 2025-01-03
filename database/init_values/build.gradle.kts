plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.kotlinSerialization)
}

dependencies {
    /**
     * PROJECT MODULES
     */
    implementation(projects.modelShared)
    implementation(projects.database.tables)
    /**
     * DEPENDENCIES
     */
    implementation(libs.bundles.database)
}