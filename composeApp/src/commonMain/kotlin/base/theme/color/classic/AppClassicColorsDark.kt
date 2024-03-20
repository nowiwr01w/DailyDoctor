package base.theme.color.classic

import base.theme.color.data.AppColorsData
import base.theme.color.data.background.classic.ClassicDarkBackgroundColors
import base.theme.color.data.text.classic.ClassicDarkTextColors

internal val classicAppColorsDark by lazy {
    AppColorsData(
        appTextColors = ClassicDarkTextColors(),
        appBackgroundColors = ClassicDarkBackgroundColors()
    )
}