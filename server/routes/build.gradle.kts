plugins {
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.kotlinSerialization)
}

dependencies {
    /**
     * SHARED MODELS
     */
    implementation(projects.modelShared)
    /**
     * SERVER
     */
    implementation(projects.server.domain)
    /**
     * ENCRYPTION
     */
    implementation(projects.encryption.shared)
    implementation(projects.encryption.server)
    /**
     * DEPENDENCIES
     */
    implementation(libs.bundles.server)
}