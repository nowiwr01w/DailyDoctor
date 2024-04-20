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
            baseName = "model"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kodein.compose)
                implementation(libs.coroutines)
                implementation(libs.kotlin.serialization.json)
            }
        }
    }
}

android {
    namespace = "nowiwr01p.daily.doctor.model"
    compileSdk = libs.versions.android.targetSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}