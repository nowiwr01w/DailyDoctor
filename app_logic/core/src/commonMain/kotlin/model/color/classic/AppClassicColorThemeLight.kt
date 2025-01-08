package model.color.classic

import model.color.data.border.classic.ClassicLightBorderColors
import model.color.data.text.classic.ClassicLightTextColors
import model.color.AppColorTheme
import com.nowiwr01p.model.model.app_config.AppColorThemeType
import com.nowiwr01p.model.model.app_config.AppColorThemeType.*
import model.color.data.AppColorsData
import model.color.data.background.classic.ClassicLightBackgroundColors

data class AppClassicColorThemeLight(
    override val type: AppColorThemeType = CLASSIC_LIGHT,
    override val appColorsData: AppColorsData = appClassicColorsDataLight
): AppColorTheme

private val appClassicColorsDataLight = AppColorsData(
    appTextColors = ClassicLightTextColors(),
    appBackgroundColors = ClassicLightBackgroundColors(),
    appBorderColors = ClassicLightBorderColors()
)