package domain.repository.brand

import domain.model.color.AppColors
import domain.model.color.classic.AppClassicColors

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