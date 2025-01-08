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
                implementation(projects.shared)
                /**
                 * BASE API CLIENT
                 */
                implementation(projects.shared.baseApiClient)
                /**
                 * APP SHARED
                 */
                implementation(projects.appLogic.core)
                implementation(projects.appLogic.domain)
                implementation(projects.appLogic.platform)
                /**
                 * LOCAL DATABASE
                 */
                implementation(projects.appLogic.localDatabase.domain)
                /**
                 * ENCRYPTION
                 */
                implementation(projects.shared.encryption.shared)
                implementation(projects.shared.encryption.client)
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
