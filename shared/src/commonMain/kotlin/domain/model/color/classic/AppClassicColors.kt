package domain.model.color.classic

import domain.model.color.AppColors
import domain.model.color.AppColorsData

data class AppClassicColors(
    override val appColorsLight: AppColorsData = classicAppColorsLight,
    override val appColorsDark: AppColorsData = classicAppColorsDark
): AppColors