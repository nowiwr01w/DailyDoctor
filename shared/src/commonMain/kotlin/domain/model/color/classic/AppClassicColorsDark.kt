package domain.model.color.classic

import domain.model.AppColorsData
import domain.model.color.data.background.classic.ClassicDarkBackgroundColors
import domain.model.color.data.text.classic.ClassicDarkTextColors

internal val classicAppColorsDark by lazy {
    AppColorsData(
        appTextColors = ClassicDarkTextColors(),
        appBackgroundColors = ClassicDarkBackgroundColors()
    )
}