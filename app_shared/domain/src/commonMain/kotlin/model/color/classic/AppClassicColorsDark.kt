package model.color.classic

import model.color.AppColorsData
import model.color.data.background.classic.ClassicDarkBackgroundColors
import model.color.data.border.classic.ClassicDarkBorderColors
import model.color.data.text.classic.ClassicDarkTextColors

internal val classicAppColorsDark by lazy {
    AppColorsData(
        appTextColors = ClassicDarkTextColors(),
        appBackgroundColors = ClassicDarkBackgroundColors(),
        appBorderColors = ClassicDarkBorderColors()
    )
}