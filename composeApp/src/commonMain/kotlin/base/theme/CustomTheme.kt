package base.theme

import androidx.compose.runtime.Composable
import base.theme.color.data.AppColorsData
import base.theme.color.data.LocalAppColorsData

object CustomTheme {
    val colors: AppColorsData
        @Composable
        get() = LocalAppColorsData.current
}