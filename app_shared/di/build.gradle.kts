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
                jvmTarget = "11"
            }
        }
    }
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared.di"
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
                implementation(projects.appShared.domain)
                implementation(projects.appShared.data)
                implementation(projects.appShared.works)
                implementation(projects.appShared.works.di)
                implementation(projects.appShared.config.di)
                /**
                 * DEPENDENCIES
                 */
                implementation(libs.logback)
                implementation(libs.bundles.base.app)
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
    namespace = "nowiwr01p.daily.doctor.shared.di"
    compileSdk = libs.versions.android.targetSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}