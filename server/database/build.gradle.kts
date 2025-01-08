plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.kotlinSerialization)
}

dependencies {
    /**
     * SHARED MODELS
     */
    implementation(projects.modelShared)
    /**
     * DATABASE
     */
    implementation(projects.server.database.di)
    implementation(projects.server.database.tables)
    implementation(projects.server.database.initValues)
    /**
     * DEPENDENCIES
     */
    implementation(libs.bundles.database)
}