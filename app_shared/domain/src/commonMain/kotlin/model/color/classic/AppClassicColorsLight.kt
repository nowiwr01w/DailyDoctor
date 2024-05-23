package model.color.classic

import model.color.AppColorsData
import model.color.data.background.classic.ClassicLightBackgroundColors
import model.color.data.border.classic.ClassicLightBorderColors
import model.color.data.text.classic.ClassicLightTextColors

internal val classicAppColorsLight by lazy {
    AppColorsData(
        appTextColors = ClassicLightTextColors(),
        appBackgroundColors = ClassicLightBackgroundColors(),
        appBorderColors = ClassicLightBorderColors()
    )
}