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
            baseName = "local_database.di"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                /**
                 * SHARED MODELS
                 */
                implementation(projects.modelShared)
                /**
                 * LOCAL DATABASE
                 */
                implementation(projects.localDatabase.data)
                implementation(projects.localDatabase.domain)
                /**
                 * DEPENDENCIES
                 */
                implementation(libs.bundles.local.database)
            }
        }
        androidMain.dependencies {
            implementation(libs.koin.android)
        }
    }

    /**
     * Cannot locate tasks that match ':model_shared:testClasses'
     * as task 'testClasses' not found in project ':model_shared'.
     */
    task("testClasses") // TODO
}

android {
    namespace = "nowiwr01p.daily.doctor.local_database.di"
    compileSdk = libs.versions.android.targetSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}