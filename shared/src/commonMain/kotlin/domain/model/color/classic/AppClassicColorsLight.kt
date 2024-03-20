package domain.model.color.classic

import domain.model.AppColorsData
import domain.model.color.data.background.classic.ClassicLightBackgroundColors
import domain.model.color.data.text.classic.ClassicLightTextColors

internal val classicAppColorsLight by lazy {
    AppColorsData(
        appTextColors = ClassicLightTextColors(),
        appBackgroundColors = ClassicLightBackgroundColors()
    )
}