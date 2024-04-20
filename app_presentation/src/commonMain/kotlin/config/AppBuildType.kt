package config

enum class AppBuildType(val type: String) {
    LOCAL("local"),
    GIT("git"),
    REMOTE("remote")
}

internal val appBuildType = AppBuildType.LOCAL