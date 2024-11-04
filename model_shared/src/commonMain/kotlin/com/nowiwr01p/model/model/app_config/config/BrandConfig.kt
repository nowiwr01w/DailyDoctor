package com.nowiwr01p.model.model.app_config.config

import com.nowiwr01p.model.model.app_config.AppColorThemeType.CLASSIC_LIGHT
import com.nowiwr01p.model.model.app_config.settings.app.BrandSettings
import com.nowiwr01p.model.model.app_config.settings.server.ServerSettings
import kotlinx.serialization.Serializable

@Serializable
sealed interface BrandConfig {
    val brandSettings: BrandSettings
    val serverSettings: ServerSettings
}

/**
 * CALL DOCTOR
 */
@Serializable
data class CallDoctorBrandConfig(
    override val brandSettings: BrandSettings = callDoctorBrandSettings,
    override val serverSettings: ServerSettings = callDoctorServerSettings
): BrandConfig

private val callDoctorServerSettings = ServerSettings(
    serverUrl = "https://google.com" // TODO
)

private val callDoctorBrandSettings = BrandSettings(
    brandConfigType = BrandConfigType.CALL_DOCTOR_CONFIG_TYPE,
    availableColorThemes = listOf(CLASSIC_LIGHT),
    isOnboardingEnabled = true
)