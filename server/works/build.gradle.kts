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
     * SERVER
     */
    implementation(projects.server.domain)
    /**
     * DEPENDENCIES
     */
    implementation(libs.bundles.server)
}