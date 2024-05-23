package base.theme

import androidx.compose.runtime.Composable
import model.color.AppColorsData
import model.color.LocalAppColorsData

object CustomTheme {
    val colors: AppColorsData
        @Composable
        get() = LocalAppColorsData.current
}