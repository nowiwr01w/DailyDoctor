package theme

import androidx.compose.runtime.Composable
import model.color.data.AppColorsData
import model.color.data.LocalAppColorsData

object CustomTheme {
    val colors: AppColorsData
        @Composable
        get() = LocalAppColorsData.current
}