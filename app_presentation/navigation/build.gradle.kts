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
            baseName = "app_presentation.navigation"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                /**
                 * MODEL SHARED
                 */
                implementation(projects.shared)
                /**
                 * APP SHARED
                 */
                implementation(projects.appShared.core)
                /**
                 * APP PRESENTATION
                 */
                implementation(projects.appPresentation.viewModels.base)
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
                implementation(libs.coroutines)
            }
        }
        androidMain.dependencies {
            implementation(projects.appPresentation.viewModels.mobile)
            implementation(libs.bundles.android)
        }
        iosMain.dependencies {
            implementation(projects.appPresentation.viewModels.mobile)
            implementation(libs.bundles.ios)
        }
        val desktopMain by getting {
            dependencies {
                implementation(projects.appPresentation.viewModels.desktop)
                implementation(libs.bundles.desktop)
            }
        }
        val wasmJsMain by getting {
            dependencies {
                implementation(projects.appPresentation.viewModels.web)
                implementation(libs.bundles.web)
            }
        }
    }
}

android {
    namespace = "nowiwr01p.daily.doctor.app_presentation.navigation"
    compileSdk = libs.versions.android.targetSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
