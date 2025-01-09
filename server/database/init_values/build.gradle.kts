plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.kotlinSerialization)
}

dependencies {
    /**
     * PROJECT MODULES
     */
    implementation(projects.shared)
    implementation(projects.server.database.tables)
    /**
     * DEPENDENCIES
     */
    implementation(libs.bundles.database)
}