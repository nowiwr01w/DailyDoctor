package model.brand

import model.color.AppColors
import model.color.classic.AppClassicColors

sealed class AppBrand(
    open val name: String,
    open val appColors: AppColors
) {
    data class AppBrandClassic(
        override val name: String = "Classic",
        override val appColors: AppColors = AppClassicColors()
    ): AppBrand(
        name = name,
        appColors = appColors
    )
}