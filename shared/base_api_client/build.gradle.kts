plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinSerialization)
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
            baseName = "base_api_client"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                /**
                 * SHARED MODULES
                 */
                implementation(projects.shared)
                implementation(projects.appLogic.platform)
                implementation(projects.shared.encryption.client)
                implementation(projects.shared.encryption.shared)
                /**
                 * KOIN
                 */
                implementation(libs.koin)
                /**
                 * KOTLIN
                 */
                implementation(libs.coroutines)
                implementation(libs.kotlin.date.time)
                implementation(libs.kotlin.serialization.json)
                /**
                 * KTOR CLIENT
                 */
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.logging)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.kotlin.serialization)
            }
        }
        androidMain.dependencies {
            implementation(libs.bundles.android)
        }
        val desktopMain by getting {
            dependencies {
                implementation(libs.bundles.desktop)
            }
        }
        iosMain.dependencies {
            implementation(libs.bundles.ios)
        }
        wasmJsMain.dependencies {
            implementation(libs.bundles.web)
        }
    }
}

android {
    namespace = "nowiwr01p.daily.doctor.base_api_client"
    compileSdk = libs.versions.android.targetSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}