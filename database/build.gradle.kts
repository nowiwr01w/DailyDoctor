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
    implementation(projects.database.di)
    implementation(projects.database.tables)
    implementation(projects.database.initValues)
    /**
     * DEPENDENCIES
     */
    implementation(libs.bundles.database)
}