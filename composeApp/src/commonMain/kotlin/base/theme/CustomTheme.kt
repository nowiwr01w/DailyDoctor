package base.theme

import androidx.compose.runtime.Composable
import domain.model.AppColorsData
import domain.model.LocalAppColorsData

object CustomTheme {
    val colors: AppColorsData
        @Composable
        get() = LocalAppColorsData.current
}