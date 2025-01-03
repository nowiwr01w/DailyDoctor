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
    implementation(projects.newResources)
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
     * TELEGRAM SMS
     */
    implementation(projects.tgSms.domain)
    /**
     * DEPENDENCIES
     */
    implementation(libs.bundles.server)
}
