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
    implementation(projects.server.token)
    implementation(projects.server.domain)
    /**
     * DATABASE
     */
    implementation(projects.database.domain)
    /**
     * DEPENDENCIES
     */
    implementation(libs.bundles.server)
}
