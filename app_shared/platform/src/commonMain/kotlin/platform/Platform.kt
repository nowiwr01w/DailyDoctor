package platform

enum class Platform {
    ANDROID,
    IOS,
    DESKTOP,
    WEB
}

expect val currentPlatform: Platform