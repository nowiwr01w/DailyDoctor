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
     * ENCRYPTION
     */
    implementation(projects.shared.encryption.di)
    implementation(projects.shared.encryption.server)
    /**
     * SERVER
     */
    implementation(projects.server.domain)
    implementation(projects.server.data)
    implementation(projects.server.works)
    implementation(projects.server.token)
    implementation(projects.server.routes)
    /**
     * DATABASE
     */
    implementation(projects.server.database.di)
    implementation(projects.server.database.domain)
    /**
     * TELEGRAM
     */
    implementation(projects.server.tgSms.di)
    implementation(projects.server.tgSms.domain)
    /**
     * DEPENDENCIES
     */
    implementation(libs.bundles.server)
}