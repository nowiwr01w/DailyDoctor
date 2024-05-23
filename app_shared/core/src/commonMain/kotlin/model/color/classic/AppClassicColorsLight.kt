package core.model.color.classic

import core.model.color.AppColorsData
import core.model.color.data.background.classic.ClassicLightBackgroundColors
import core.model.color.data.border.classic.ClassicLightBorderColors
import core.model.color.data.text.classic.ClassicLightTextColors

internal val classicAppColorsLight by lazy {
    AppColorsData(
        appTextColors = ClassicLightTextColors(),
        appBackgroundColors = ClassicLightBackgroundColors(),
        appBorderColors = ClassicLightBorderColors()
    )
}