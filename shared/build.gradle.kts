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
            baseName = "model"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                /**
                 * PROJECT MODULES
                 */
                implementation(projects.appLogic.resources)
                /**
                 * DEPENDENCIES
                 */
                implementation(libs.koin)
                implementation(libs.coroutines)
                implementation(libs.kotlin.serialization.json)
                /**
                 * COMPOSE
                 */
                implementation(compose.runtime)
                implementation(compose.components.resources)
            }
        }
    }

    /**
     * Cannot locate tasks that match ':model_shared:testClasses'
     * as task 'testClasses' not found in project ':model_shared'.
     */
    task("testClasses") // TODO
}

android {
    namespace = "nowiwr01p.daily.doctor.model"
    compileSdk = libs.versions.android.targetSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}