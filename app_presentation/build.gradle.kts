import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        moduleName = "composeApp"
        browser {
            commonWebpackConfig {
                outputFileName = "composeApp.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(project.projectDir.path)
                    }
                }
            }
        }
        binaries.executable()
    }
    
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }
    
    jvm("desktop")
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        val desktopMain by getting
        
        androidMain.dependencies {
            /**
             * SHARED MOBILE MODULE
             */
            implementation(projects.appPresentation.appMobile)
            /**
             * VIEW MODELS
             */
            implementation(projects.appPresentation.viewModels.shared)
            implementation(projects.appPresentation.viewModels.mobile)
            /**
             * DEPENDENCIES
             */
            implementation(libs.bundles.android)
        }
        iosMain.dependencies {
            /**
             * SHARED MOBILE MODULE
             */
            implementation(projects.appPresentation.appMobile)
            /**
             * VIEW MODELS
             */
            implementation(projects.appPresentation.viewModels.shared)
            implementation(projects.appPresentation.viewModels.mobile)
            /**
             * DEPENDENCIES
             */
            implementation(libs.bundles.ios)
        }
        commonMain.dependencies {
            /**
             * MODEL SHARED
             */
            implementation(projects.modelShared)
            /**
             * APP SHARED
             */
            implementation(projects.appShared)
            implementation(projects.appShared.core)
            implementation(projects.appShared.domain)
            implementation(projects.appShared.data)
            implementation(projects.appShared.works)
            implementation(projects.appShared.platform)
            /**
             * APP PRESENTATION
             */
            implementation(projects.appPresentation.coreUi)
            implementation(projects.appPresentation.platform)
            implementation(projects.appPresentation.theme)
            implementation(projects.appPresentation.viewModels)
            implementation(projects.appPresentation.navigation)
            implementation(projects.appPresentation.viewModels.di)
            implementation(projects.appPresentation.viewModels.base)
            implementation(projects.appPresentation.di)
            /**
             * LOCAL DATABASE
             */
            implementation(projects.localDatabase)
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
        desktopMain.dependencies {
            /**
             * COMPOSE
             */
            implementation(compose.desktop.currentOs)
            /**
             * VIEW MODELS
             */
            implementation(projects.appPresentation.viewModels.shared)
            implementation(projects.appPresentation.viewModels.desktop)
            /**
             * DEPENDENCIES
             */
            implementation(libs.bundles.desktop)
        }
        val wasmJsMain by getting {
            dependencies {
                /**
                 * VIEW MODELS
                 */
                implementation(projects.appPresentation.viewModels.shared)
                implementation(projects.appPresentation.viewModels.web)
                /**
                 * DEPENDENCIES
                 */
                implementation(libs.bundles.web)
            }
        }
    }
}

android {
    namespace = "nowiwr01p.daily.doctor"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "nowiwr01p.daily.doctor"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "nowiwr01p.daily.doctor"
            packageVersion = "1.0.0"
        }
    }
}

compose.experimental {
    web.application {}
}