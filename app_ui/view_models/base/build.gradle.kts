import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.compose.compiler)
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
            baseName = "app_ui.view_models.base"
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
                implementation(projects.appLogic.core)
                implementation(projects.appLogic.domain)
                /**
                 * COMPOSE
                 */
                implementation(compose.runtime)
                /**
                 * RESOURCES
                 */
                implementation(projects.appLogic.resources)
                /**
                 * DEPENDENCIES
                 */
                implementation(libs.koin.compose)
                implementation(libs.coroutines)
                implementation(libs.bundles.base.app)
                implementation(libs.bundles.mvi)
            }
        }
        androidMain.dependencies {
            implementation(libs.bundles.android)
        }
        iosMain.dependencies {
            implementation(libs.bundles.ios)
        }
        val desktopMain by getting {
            dependencies {
                implementation(libs.bundles.desktop)
            }
        }
        val wasmJsMain by getting {
            dependencies {
                implementation(libs.bundles.web)
            }
        }
    }
}

android {
    namespace = "nowiwr01p.daily.doctor.app_ui.view_models.base"
    compileSdk = libs.versions.android.targetSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
