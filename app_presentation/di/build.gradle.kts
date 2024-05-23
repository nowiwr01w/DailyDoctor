plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.kotlinSerialization)
}

dependencies {
    /**
     * SHARED MODELS
     */
    implementation(projects.modelShared)
    implementation(projects.appShared)
    implementation(projects.appShared.di)
    /**
     * DEPENDENCIES
     */
    implementation(libs.bundles.base.app)
}