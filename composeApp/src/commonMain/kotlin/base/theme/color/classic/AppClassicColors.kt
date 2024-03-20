package base.theme.color.classic

import base.theme.color.AppColors
import base.theme.color.data.AppColorsData

internal data class AppClassicColors(
    override val appColorsLight: AppColorsData = classicAppColorsLight,
    override val appColorsDark: AppColorsData = classicAppColorsDark
): AppColors