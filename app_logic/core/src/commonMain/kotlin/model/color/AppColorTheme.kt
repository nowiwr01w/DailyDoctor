package model.color

import model.color.classic.AppClassicColorThemeDark
import model.color.classic.AppClassicColorThemeLight
import com.nowiwr01p.model.model.app_config.AppColorThemeType
import model.color.data.AppColorsData

interface AppColorTheme {
    val type: AppColorThemeType
    val appColorsData: AppColorsData
}

val allAppColorThemes = listOf(
    AppClassicColorThemeLight(),
    AppClassicColorThemeDark()
)