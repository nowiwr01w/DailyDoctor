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
                implementation(projects.shared)
                /**
                 * APP SHARED
                 */
                implementation(projects.appLogic)
                implementation(projects.appLogic.core)
                implementation(projects.appLogic.di)
                implementation(projects.appLogic.domain)
                implementation(projects.appLogic.works)
                implementation(projects.appLogic.platform)
                /**
                 * APP PRESENTATION
                 */
                implementation(projects.appUi.coreUi.di)
                implementation(projects.appUi.viewModels.di)
                implementation(projects.appUi.navigation.di)
                /**
                 * LOCAL DATABASE
                 */
                implementation(projects.appLogic.localDatabase.di)
                implementation(projects.appLogic.localDatabase.domain)
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

    /**
     * Cannot locate tasks that match ':model_shared:testClasses'
     * as task 'testClasses' not found in project ':model_shared'.
     */
    task("testClasses") // TODO
}

android {
    namespace = "nowiwr01p.daily.doctor.app_presentation.di"
    compileSdk = libs.versions.android.targetSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    packaging {
        resources {
            excludes += "META-INF/INDEX.LIST"
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}