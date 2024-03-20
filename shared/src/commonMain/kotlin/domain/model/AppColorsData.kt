package domain.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import domain.model.color.data.background.AppBackgroundColors
import domain.model.color.data.text.AppTextColors

data class AppColorsData(
    private val appTextColors: AppTextColors,
    private val appBackgroundColors: AppBackgroundColors
) {
    var textColors by mutableStateOf(appTextColors)
        private set

    var backgroundColors by mutableStateOf(appBackgroundColors)
        private set

    fun updateColors(other: AppColorsData) {
        textColors = other.appTextColors
        backgroundColors = other.appBackgroundColors
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