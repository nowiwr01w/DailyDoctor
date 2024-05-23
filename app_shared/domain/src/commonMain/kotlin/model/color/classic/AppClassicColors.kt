package model.color.classic

import model.color.AppColorsData
import model.color.AppColors

data class AppClassicColors(
    override val appColorsLight: AppColorsData = classicAppColorsLight,
    override val appColorsDark: AppColorsData = classicAppColorsDark
): AppColors