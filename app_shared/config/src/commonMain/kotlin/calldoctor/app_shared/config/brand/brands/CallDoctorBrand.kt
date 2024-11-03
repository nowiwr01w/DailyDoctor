package calldoctor.app_shared.config.brand.brands

import calldoctor.app_shared.config.brand.AppBrand
import calldoctor.app_shared.config.settings.app.AppSettings
import calldoctor.app_shared.config.settings.app.AppSettingsRelease
import calldoctor.app_shared.config.settings.server.ServerSettings
import calldoctor.app_shared.config.settings.server.ServerSettingsRelease
import model.color.data.AppColorThemeType.CLASSIC_LIGHT

data class CallDoctorBrand(
    override val appSettings: AppSettings,
    override val serverSettings: ServerSettings
): AppBrand

internal val callDoctorAppSettings = AppSettingsRelease(
    availableColorThemes = listOf(CLASSIC_LIGHT),
    isOnboardingEnabled = true
)

internal val callDoctorServerSettings = ServerSettingsRelease(
    serverUrl = "" // TODO
)