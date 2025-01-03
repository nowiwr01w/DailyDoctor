plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
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
            baseName = "new_resources"
        }
    }

    sourceSets {
        commonMain.dependencies {
            /**
             * COMPOSE
             */
            implementation(compose.runtime)
            implementation(compose.ui)
        }
    }
}

android {
    namespace = "com.nowiwr01p.model.resources"
    compileSdk = libs.versions.android.targetSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
