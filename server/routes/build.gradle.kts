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
    implementation(projects.modelShared.encryption.shared)
    implementation(projects.modelShared.encryption.server)
    /**
     * DEPENDENCIES
     */
    implementation(libs.bundles.server)
}