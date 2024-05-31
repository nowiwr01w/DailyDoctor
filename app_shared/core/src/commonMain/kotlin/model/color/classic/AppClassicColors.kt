package model.color.classic

import core.model.color.AppColorsData
import core.model.color.AppColors
import core.model.color.classic.classicAppColorsDark
import core.model.color.classic.classicAppColorsLight

data class AppClassicColors(
    override val appColorsLight: AppColorsData = classicAppColorsLight,
    override val appColorsDark: AppColorsData = classicAppColorsDark
): AppColors