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
    implementation(projects.server.token)
    implementation(projects.server.domain)
    /**
     * DATABASE
     */
    implementation(projects.server.database.domain)
    /**
     * TELEGRAM SMS
     */
    implementation(projects.server.tgSms.domain)
    /**
     * DEPENDENCIES
     */
    implementation(libs.bundles.server)
}
