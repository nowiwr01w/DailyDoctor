package base.theme

import androidx.compose.runtime.Composable
import domain.model.color.AppColorsData
import domain.model.color.LocalAppColorsData

object CustomTheme {
    val colors: AppColorsData
        @Composable
        get() = LocalAppColorsData.current
}