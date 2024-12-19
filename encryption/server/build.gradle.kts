import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.jetbrainsCompose)
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
            baseName = "encryption.server"
        }
    }

    sourceSets {
        commonMain.dependencies {
            /**
             * PROJECT MODULES
             */
            implementation(projects.modelShared)
            implementation(projects.encryption.shared)
            /**
             * DEPENDENCIES
             */
            implementation(libs.bundles.encryption)
            /**
             * COMPOSE
             */
            implementation(compose.runtime)
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

    /**
     * Cannot locate tasks that match ':model_shared:testClasses'
     * as task 'testClasses' not found in project ':model_shared'.
     */
    task("testClasses") // TODO
}

android {
    namespace = "nowiwr01p.daily.doctor.encryption.server"
    compileSdk = libs.versions.android.targetSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}