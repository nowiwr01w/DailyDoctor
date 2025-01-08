plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.kotlinSerialization)
}

dependencies {
    /**
     * PROJECT MODULES
     */
    implementation(projects.modelShared)
    implementation(projects.server.database.domain)
    implementation(projects.server.database.data)
    implementation(projects.server.database.initValues)
    /**
     * DEPENDENCIES
     */
    implementation(libs.bundles.database)
}