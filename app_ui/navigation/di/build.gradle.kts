import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    applyDefaultHierarchyTemplate()

    @OptIn(ExperimentalWasmDsl::class)
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
            baseName = "app_ui.navigation.di"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                /**
                 * SHARED MODELS
                 */
                implementation(projects.shared)
                /**
                 * APP SHARED
                 */
                implementation(projects.appLogic.core)
                /**
                 * APP PRESENTATION
                 */
                implementation(projects.appUi.navigation)
                /**
                 * DEPENDENCIES
                 */
                implementation(libs.koin)
                implementation(libs.decompose)
            }
        }
        androidMain.dependencies {
            implementation(projects.appUi.navigation.mobile)
            implementation(libs.bundles.android)
        }
        iosMain.dependencies {
            implementation(projects.appUi.navigation.mobile)
            implementation(libs.bundles.ios)
        }
        val desktopMain by getting {
            dependencies {
                implementation(projects.appUi.navigation)
                implementation(libs.bundles.desktop)
            }
        }
        val wasmJsMain by getting {
            dependencies {
                implementation(projects.appUi.navigation)
                implementation(libs.bundles.web)
            }
        }
    }
}

android {
    namespace = "nowiwr01p.daily.doctor.app_ui.navigation.di"
    compileSdk = libs.versions.android.targetSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
