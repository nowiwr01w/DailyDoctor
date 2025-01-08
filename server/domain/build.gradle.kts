plugins {
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.kotlinSerialization)
}

dependencies {
    /**
     * SHARED MODELS
     */
    implementation(projects.shared)
    /**
     * DATABASE
     */
    implementation(projects.server.database)
    /**
     * DEPENDENCIES
     */
    implementation(libs.bundles.database)
    implementation(libs.bundles.server)
}