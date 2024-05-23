package core.model.color.classic

import core.model.color.AppColorsData
import core.model.color.AppColors

data class AppClassicColors(
    override val appColorsLight: AppColorsData = classicAppColorsLight,
    override val appColorsDark: AppColorsData = classicAppColorsDark
): AppColors