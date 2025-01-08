rootProject.name = "CallDoctor"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental")
        maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev")
        maven("https://maven.pkg.jetbrains.space/public/p/ktor/eap")
        maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental")
        maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev")
        maven("https://maven.pkg.jetbrains.space/public/p/ktor/eap")
        maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    }
}

/**
 * APP PRESENTATION
 */
include(":app_presentation")
include(":app_presentation:app_mobile")
include(":app_presentation:di")
include(":app_presentation:platform")

include(":app_presentation:core_ui")
include(":app_presentation:core_ui:di")

include(":app_presentation:navigation")
include(":app_presentation:navigation:di")
include(":app_presentation:navigation:mobile")
include(":app_presentation:navigation:model")

include(":app_presentation:view_models")
include(":app_presentation:view_models:base")
include(":app_presentation:view_models:shared")
include(":app_presentation:view_models:mobile")
include(":app_presentation:view_models:desktop")
include(":app_presentation:view_models:web")
include(":app_presentation:view_models:di")

include(":app_presentation:dialogs")

/**
 * APP SHARED
 */
include(":app_shared")
include(":app_shared:core")
include(":app_shared:platform")
include(":app_shared:domain")
include(":app_shared:data")
include(":app_shared:di")

include(":app_shared:config")
include(":app_shared:config:di")

include(":app_shared:local_database")
include(":app_shared:local_database:domain")
include(":app_shared:local_database:data")
include(":app_shared:local_database:di")

include(":app_shared:works")
include(":app_shared:works:di")

include(":app_shared:resources")

/**
 * DATABASE
 */
include(":server:database")
include(":server:database:domain")
include(":server:database:data")
include(":server:database:tables")
include(":server:database:di")
include(":server:database:init_values")

/**
 * SHARED
 */
include(":model_shared")

include(":model_shared:base_api_client")

include(":model_shared:encryption")
include(":model_shared:encryption:client")
include(":model_shared:encryption:shared")
include(":model_shared:encryption:server")
include(":model_shared:encryption:di")

/**
 * SERVER
 */
include(":server")
include(":server:di")
include(":server:data")
include(":server:domain")
include(":server:routes")
include(":server:token")
include(":server:works")

/**
 * TELEGRAM VERIFICATION
 */
include(":server:tg_sms")
include(":server:tg_sms:domain")
include(":server:tg_sms:data")
include(":server:tg_sms:di")
