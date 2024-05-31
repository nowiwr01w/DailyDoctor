package theme

import androidx.compose.runtime.Composable
import core.model.color.AppColorsData
import core.model.color.LocalAppColorsData

object CustomTheme {
    val colors: AppColorsData
        @Composable
        get() = LocalAppColorsData.current
}