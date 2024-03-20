package base.theme.color.classic

import base.theme.color.data.AppColorsData
import base.theme.color.data.background.classic.ClassicLightBackgroundColors
import base.theme.color.data.text.classic.ClassicLightTextColors

internal val classicAppColorsLight by lazy {
    AppColorsData(
        appTextColors = ClassicLightTextColors(),
        appBackgroundColors = ClassicLightBackgroundColors()
    )
}