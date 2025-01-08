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
     * SERVER
     */
    implementation(projects.server.domain)
    /**
     * ENCRYPTION
     */
    implementation(projects.shared.encryption.shared)
    implementation(projects.shared.encryption.server)
    /**
     * DEPENDENCIES
     */
    implementation(libs.bundles.server)
}