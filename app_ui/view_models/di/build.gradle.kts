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
            baseName = "app_presentation.view_models.di"
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
                implementation(projects.appLogic.works)
                /**
                 * APP PRESENTATION
                 */
                implementation(projects.appUi.coreUi)
                implementation(projects.appUi.viewModels.shared)
                implementation(projects.appUi.viewModels.mobile)
                implementation(projects.appUi.viewModels.desktop)
                implementation(projects.appUi.viewModels.web)
                implementation(projects.appUi.navigation)
                implementation(projects.appUi.navigation.model)
                implementation(projects.appUi.dialogs)
                /**
                 * LOCAL DATABASE
                 */
                implementation(projects.appLogic.localDatabase.domain)
                /**
                 * DEPENDENCIES
                 */
                implementation(libs.koin)
                implementation(libs.coroutines)
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
    namespace = "nowiwr01p.daily.doctor.app_presentation.view_models.di"
    compileSdk = libs.versions.android.targetSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
