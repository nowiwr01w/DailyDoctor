package model.color.classic

import model.color.AppColorTheme
import model.color.data.AppColorThemeType
import model.color.data.AppColorThemeType.CLASSIC_DARK
import model.color.data.AppColorsData
import model.color.data.background.classic.ClassicLightBackgroundColors
import model.color.data.border.classic.ClassicLightBorderColors
import model.color.data.text.classic.ClassicLightTextColors

data class AppClassicColorThemeDark(
    override val type: AppColorThemeType = CLASSIC_DARK,
    override val appColorsData: AppColorsData = appClassicColorsDataDark
): AppColorTheme

private val appClassicColorsDataDark = AppColorsData(
    appTextColors = ClassicLightTextColors(),
    appBackgroundColors = ClassicLightBackgroundColors(),
    appBorderColors = ClassicLightBorderColors()
)