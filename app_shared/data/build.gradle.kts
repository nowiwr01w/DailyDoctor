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
            baseName = "shared.data"
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
                 * BASE API CLIENT
                 */
                implementation(projects.baseApiClient)
                /**
                 * APP SHARED
                 */
                implementation(projects.appShared.core)
                implementation(projects.appShared.domain)
                implementation(projects.appShared.platform)
                /**
                 * LOCAL DATABASE
                 */
                implementation(projects.localDatabase.domain)
                /**
                 * ENCRYPTION
                 */
                implementation(projects.encryption.shared)
                implementation(projects.encryption.client)
                /**
                 * DEPENDENCIES
                 */
                implementation(libs.bundles.base.app)
            }
        }
    }
}

android {
    namespace = "nowiwr01p.daily.doctor.shared.data"
    compileSdk = libs.versions.android.targetSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}