package model.color.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import model.color.data.background.AppBackgroundColors
import model.color.data.border.AppBorderColors
import model.color.data.text.AppTextColors

data class AppColorsData(
    private val appTextColors: AppTextColors,
    private val appBackgroundColors: AppBackgroundColors,
    private val appBorderColors: AppBorderColors
) {
    var textColors by mutableStateOf(appTextColors)
        private set

    var backgroundColors by mutableStateOf(appBackgroundColors)
        private set

    var borderColors by mutableStateOf(appBorderColors)
        private set

    fun updateColors(other: AppColorsData) {
        textColors = other.appTextColors
        backgroundColors = other.appBackgroundColors
        borderColors = other.borderColors
    }
}

val LocalAppColorsData = staticCompositionLocalOf<AppColorsData> {
    error("No CustomColors with AppColorsData set")
}

@Composable
fun ProvideCustomColors(
    colors: AppColorsData,
    content: @Composable () -> Unit
) {
    val colorPalette = remember { colors }
    colorPalette.updateColors(colors)
    CompositionLocalProvider(LocalAppColorsData provides colorPalette) {
        content()
    }
}