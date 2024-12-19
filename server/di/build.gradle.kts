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
     * ENCRYPTION
     */
    implementation(projects.encryption.di)
    implementation(projects.encryption.server)
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
    implementation(projects.database.di)
    implementation(projects.database.domain)
    /**
     * TELEGRAM
     */
    implementation(projects.tgSms.di)
    implementation(projects.tgSms.domain)
    /**
     * DEPENDENCIES
     */
    implementation(libs.bundles.server)
}