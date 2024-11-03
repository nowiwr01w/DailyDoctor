package calldoctor.app_shared.config.config.configs

import calldoctor.app_shared.config.brand.AppBrand
import calldoctor.app_shared.config.brand.brands.CallDoctorBrand
import calldoctor.app_shared.config.brand.brands.callDoctorAppSettings
import calldoctor.app_shared.config.brand.brands.callDoctorServerSettings
import calldoctor.app_shared.config.config.AppConfig
import calldoctor.app_shared.config.settings.app.AppSettingsDebug
import calldoctor.app_shared.config.settings.server.ServerSettingsDebug

data class CallDoctorConfig(
    override val isDebug: Boolean,
    override val appBrand: AppBrand
) : AppConfig

fun getCallDoctorConfig(isDebug: Boolean) = CallDoctorConfig(
    isDebug = isDebug,
    appBrand = CallDoctorBrand(
        appSettings = when {
            isDebug -> AppSettingsDebug()
            else -> callDoctorAppSettings
        },
        serverSettings = when {
            isDebug -> ServerSettingsDebug()
            else -> callDoctorServerSettings
        }
    )
)