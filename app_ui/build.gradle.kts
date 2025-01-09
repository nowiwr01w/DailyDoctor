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
                jvmTarget = "17"
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
            implementation(projects.appUi.appMobile)
            /**
             * VIEW MODELS
             */
            implementation(projects.appUi.viewModels.shared)
            implementation(projects.appUi.viewModels.mobile)
            /**
             * NAVIGATION
             */
            implementation(projects.appUi.navigation.model)
            implementation(projects.appUi.navigation.mobile)
            /**
             * DEPENDENCIES
             */
            implementation(libs.bundles.android)
            implementation(libs.android.splash.screen)
        }
        iosMain.dependencies {
            /**
             * SHARED MOBILE MODULE
             */
            implementation(projects.appUi.appMobile)
            /**
             * VIEW MODELS
             */
            implementation(projects.appUi.di)
            implementation(projects.appUi.viewModels.shared)
            implementation(projects.appUi.viewModels.mobile)
            /**
             * NAVIGATION
             */
            implementation(projects.appUi.navigation.model)
            implementation(projects.appUi.navigation.mobile)
            /**
             * DEPENDENCIES
             */
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
            implementation(projects.appLogic)
            implementation(projects.appLogic.core)
            implementation(projects.appLogic.domain)
            implementation(projects.appLogic.data)
            implementation(projects.appLogic.works)
            implementation(projects.appLogic.platform)
            /**
             * APP PRESENTATION
             */
            implementation(projects.appUi.coreUi)
            implementation(projects.appUi.platform)
            implementation(projects.appUi.viewModels)
            implementation(projects.appUi.navigation)
            implementation(projects.appUi.viewModels.di)
            implementation(projects.appUi.viewModels.base)
            implementation(projects.appUi.di)
            /**
             * LOCAL DATABASE
             */
            implementation(projects.appLogic.localDatabase)
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
            implementation(projects.appUi.viewModels.shared)
            implementation(projects.appUi.viewModels.desktop)
            /**
             * COMPOSE
             */
            implementation(compose.ui)
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
                implementation(projects.appUi.viewModels.shared)
                implementation(projects.appUi.viewModels.web)
                /**
                 * RESOURCES
                 */
                implementation(projects.appLogic.resources)
                /**
                 * COMPOSE
                 */
                implementation(compose.ui)
                implementation(compose.components.resources)
                /**
                 * DEPENDENCIES
                 */
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
            excludes += "META-INF/INDEX.LIST"
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
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
