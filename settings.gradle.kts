rootProject.name = "DailyDoctor"
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

include(":app_presentation")

include(":app_shared")
include(":app_shared:data")
include(":app_shared:domain")
include(":app_shared:works")

include(":database")
include(":database:domain")
include(":database:data")
include(":database:di")
include(":database:tables")

include(":model_shared")

include(":server")
include(":server:di")
include(":server:data")
include(":server:domain")
include(":server:routes")
include(":server:token")
include(":server:works")