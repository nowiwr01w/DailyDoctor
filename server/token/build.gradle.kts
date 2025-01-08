plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.kotlinSerialization)
}

dependencies {
    /**
     * SHARED MODELS
     */
    implementation(projects.shared)
    /**
     * DEPENDENCIES
     */
    implementation(libs.bundles.server)
}