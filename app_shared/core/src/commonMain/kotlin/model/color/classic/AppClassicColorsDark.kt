package core.model.color.classic

import core.model.color.AppColorsData
import core.model.color.data.background.classic.ClassicDarkBackgroundColors
import core.model.color.data.border.classic.ClassicDarkBorderColors
import core.model.color.data.text.classic.ClassicDarkTextColors

internal val classicAppColorsDark by lazy {
    AppColorsData(
        appTextColors = ClassicDarkTextColors(),
        appBackgroundColors = ClassicDarkBackgroundColors(),
        appBorderColors = ClassicDarkBorderColors()
    )
}