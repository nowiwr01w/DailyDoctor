import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.kotlinSerialization)
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
            baseName = "app_presentation.view_models.shared"
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
                implementation(projects.appShared.core)
                implementation(projects.appShared.domain)
                implementation(projects.appShared.works)
                implementation(projects.appShared.platform)
                /**
                 * APP PRESENTATION
                 */
                implementation(projects.appPresentation.coreUi)
                implementation(projects.appPresentation.navigation) // TODO: navigation.model
                implementation(projects.appPresentation.navigation.model)
                implementation(projects.appPresentation.viewModels.base)
                /**
                 * COMPOSE // TODO: Remove?
                 */
                implementation(compose.runtime)
                implementation(compose.components.resources)
                /**
                 * RESOURCES
                 */
                implementation(projects.appShared.resources)
                /**
                 * DEPENDENCIES
                 */
                implementation(libs.koin)
                implementation(libs.coroutines)
                implementation(libs.kotlin.serialization.json)
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
    namespace = "nowiwr01p.daily.doctor.app_presentation.view_models.shared"
    compileSdk = libs.versions.android.targetSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
