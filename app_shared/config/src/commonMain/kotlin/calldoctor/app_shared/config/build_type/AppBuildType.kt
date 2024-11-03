package calldoctor.app_shared.config.build_type

sealed interface AppBuildType {
    data object Debug: AppBuildType
    data object Release: AppBuildType
}

// TODO: Release related
val currentBuildType = AppBuildType.Debug