package calldoctor.app_shared.config.config

import calldoctor.app_shared.config.brand.AppBrand
import calldoctor.app_shared.config.build_type.AppBuildType
import calldoctor.app_shared.config.build_type.currentBuildType
import calldoctor.app_shared.config.config.configs.getCallDoctorConfig

interface AppConfig {
    val isDebug: Boolean
    val appBrand: AppBrand
}

// TODO: Release related
val currentAppConfig = getCallDoctorConfig(isDebug = currentBuildType is AppBuildType.Debug)