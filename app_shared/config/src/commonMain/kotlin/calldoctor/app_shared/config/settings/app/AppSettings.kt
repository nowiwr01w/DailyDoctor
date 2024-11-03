package calldoctor.app_shared.config.settings.app

import model.color.data.AppColorThemeType
import model.color.data.AppColorThemeType.*

interface AppSettings {
    val availableColorThemes: List<AppColorThemeType>
    val isOnboardingEnabled: Boolean
}

/**
 * DEBUG
 */
data class AppSettingsDebug(
    override val availableColorThemes: List<AppColorThemeType> = listOf(CLASSIC_LIGHT, CLASSIC_DARK),
    override val isOnboardingEnabled: Boolean = true
): AppSettings

/**
 * RELEASE
 */
data class AppSettingsRelease(
    override val availableColorThemes: List<AppColorThemeType>,
    override val isOnboardingEnabled: Boolean
): AppSettings