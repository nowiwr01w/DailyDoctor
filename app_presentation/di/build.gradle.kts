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
            baseName = "app_presentation.di"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                /**
                 * COMPOSE
                 */
                implementation(compose.material)
                /**
                 * SHARED MODELS
                 */
                implementation(projects.modelShared)
                /**
                 * APP SHARED
                 */
                implementation(projects.appShared)
                implementation(projects.appShared.core)
                implementation(projects.appShared.di)
                implementation(projects.appShared.domain)
                implementation(projects.appShared.works)
                implementation(projects.appShared.platform)
                /**
                 * APP PRESENTATION
                 */
                implementation(projects.appPresentation.theme)
                implementation(projects.appPresentation.theme.di)
                implementation(projects.appPresentation.coreUi.di)
                implementation(projects.appPresentation.viewModels.di)
                implementation(projects.appPresentation.navigation.di)
                /**
                 * LOCAL DATABASE
                 */
                implementation(projects.localDatabase)
                /**
                 * DEPENDENCIES
                 */
                implementation(libs.bundles.base.app)
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
    namespace = "nowiwr01p.daily.doctor.app_presentation.di"
    compileSdk = libs.versions.android.targetSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}