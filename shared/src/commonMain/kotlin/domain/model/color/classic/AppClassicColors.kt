package domain.model.color.classic

import domain.model.AppColors
import domain.model.AppColorsData

data class AppClassicColors(
    override val appColorsLight: AppColorsData = classicAppColorsLight,
    override val appColorsDark: AppColorsData = classicAppColorsDark
): AppColors