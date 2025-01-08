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
 * APP UI
 */
include(":app_ui")
include(":app_ui:app_mobile")
include(":app_ui:di")
include(":app_ui:platform")

include(":app_ui:core_ui")
include(":app_ui:core_ui:di")

include(":app_ui:navigation")
include(":app_ui:navigation:di")
include(":app_ui:navigation:mobile")
include(":app_ui:navigation:model")

include(":app_ui:view_models")
include(":app_ui:view_models:base")
include(":app_ui:view_models:shared")
include(":app_ui:view_models:mobile")
include(":app_ui:view_models:desktop")
include(":app_ui:view_models:web")
include(":app_ui:view_models:di")

include(":app_ui:dialogs")

/**
 * APP LOGIC
 */
include(":app_logic")
include(":app_logic:core")
include(":app_logic:platform")
include(":app_logic:domain")
include(":app_logic:data")
include(":app_logic:di")

include(":app_logic:config")
include(":app_logic:config:di")

include(":app_logic:local_database")
include(":app_logic:local_database:domain")
include(":app_logic:local_database:data")
include(":app_logic:local_database:di")

include(":app_logic:works")
include(":app_logic:works:di")

include(":app_logic:resources")

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
include(":shared")

include(":shared:base_api_client")

include(":shared:encryption")
include(":shared:encryption:client")
include(":shared:encryption:shared")
include(":shared:encryption:server")
include(":shared:encryption:di")

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
