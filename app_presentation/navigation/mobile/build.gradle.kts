plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    applyDefaultHierarchyTemplate()

    wasmJs {
        browser {

        }
    }

    jvm("desktop")

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "app_presentation.navigation.mobile"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                /**
                 * MODEL SHARED
                 */
                implementation(projects.modelShared)
                /**
                 * APP SHARED
                 */
                implementation(projects.appShared.core)
                /**
                 * APP PRESENTATION
                 */
                implementation(projects.appPresentation.navigation)
                implementation(projects.appPresentation.navigation.model)
                implementation(projects.appPresentation.viewModels.base)
                implementation(projects.appPresentation.viewModels.mobile)
                /**
                 * COMPOSE
                 */
                implementation(compose.material)
                implementation(compose.components.resources)
                /**
                 * DEPENDENCIES
                 */
                implementation(libs.koin)
                implementation(libs.decompose)
                implementation(libs.decompose.extensions)
            }
        }
    }
}

android {
    namespace = "nowiwr01p.daily.doctor.app_presentation.navigation.mobile"
    compileSdk = libs.versions.android.targetSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
