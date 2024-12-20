plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.kotlinSerialization)
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
            baseName = "app_presentation.dialogs"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                /**
                 * MODEL SHARED
                 */
                implementation(projects.modelShared)
                /**
                 * APP SHARED
                 */
                implementation(projects.appShared.core)
                implementation(projects.appShared.domain)
                implementation(projects.appShared.platform)
                /**
                 * APP PRESENTATION
                 */
                implementation(projects.appPresentation.coreUi)
                implementation(projects.appPresentation.platform)
                implementation(projects.appPresentation.navigation)
                implementation(projects.appPresentation.navigation.mobile)
                implementation(projects.appPresentation.navigation.model)
                implementation(projects.appPresentation.viewModels.base)
                implementation(projects.appPresentation.viewModels.mobile)
                implementation(projects.appPresentation.viewModels.shared)
                implementation(projects.appPresentation.viewModels.desktop)
                implementation(projects.appPresentation.viewModels.web)
                /**
                 * RESOURCES
                 */
                implementation(projects.resources)
                /**
                 * COMPOSE
                 */
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.ui)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)
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
    namespace = "nowiwr01p.daily.doctor.app_presentation.dialogs"
    compileSdk = libs.versions.android.targetSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
