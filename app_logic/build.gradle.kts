import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser {
            commonWebpackConfig {
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(project.projectDir.path)
                    }
                }
            }
        }
    }
    
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }
    
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    
    jvm()
    
    sourceSets {
        val jvmMain by getting

        androidMain.dependencies {
            implementation(libs.bundles.android)
        }
        iosMain.dependencies {
            implementation(libs.bundles.ios)
        }
        commonMain.dependencies {
            /**
             * MODEL SHARED
             */
            implementation(projects.shared)
            /**
             * APP SHARED
             */
//            implementation(projects.appLogic.di)
            implementation(projects.appLogic.core)
            implementation(projects.appLogic.platform)
            /**
             * COMPOSE
             */
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.uiToolingPreview)
            /**
             * DEPENDENCIES
             */
            implementation(libs.bundles.base.app)
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.bundles.desktop)
        }
        val wasmJsMain by getting {
            dependencies {
                implementation(libs.bundles.web)
            }
        }
    }
}

android {
    namespace = "nowiwr01p.daily.doctor.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}