package theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.font.FontFamily
import components.LocalWindowInsetsData
import components.getWindowInsets
import model.color.AppColorTheme
import model.color.data.AppColorsData
import org.koin.compose.koinInject
import org.koin.core.qualifier.named
import platform.currentPlatform
import theme.shape.AppShapes
import theme.typography.AppTypography

object CustomTheme {
    /**
     * APP COLORS
     */
    val colors: AppColorsData
        @Composable
        get() = LocalAppColorsData.current
    /**
     * APP SHAPES
     */
    val shapes: AppShapes
        @Composable
        get() = LocalShapes.current
    /**
     * APP TYPOGRAPHY
     */
    val typography: AppTypography
        @Composable
        get() = LocalTypography.current
}

@Composable
internal fun ProvideCustomTheme(
    appColorTheme: AppColorTheme,
    appFontFamily: FontFamily,
    shapes: AppShapes = koinInject(named(currentPlatform)),
    typography: AppTypography = koinInject(named(currentPlatform)),
    windowInsets: LocalWindowInsetsData = getWindowInsets().value,
    content: @Composable () -> Unit
) {
    /**
     * APP COLORS
     */
    val appColorsData by rememberUpdatedState(appColorTheme.appColorsData)
    appColorsData.updateColors(appColorsData)
    /**
     * APP TYPOGRAPHY
     */
    val appTypography by rememberUpdatedState(typography)
    appTypography.fontFamily = appFontFamily
    /**
     * APP WINDOWS INSETS
     */
    val insets by rememberUpdatedState(windowInsets)
    insets.updateInsets(insets)

    CompositionLocalProvider(
        LocalAppColorsData provides appColorsData,
        LocalShapes provides shapes,
        LocalTypography provides appTypography,
        LocalWindowInsets provides insets
    ) {
        content()
    }
}

val LocalAppColorsData = staticCompositionLocalOf<AppColorsData> {
    error("No AppColorsData set")
}
val LocalShapes = staticCompositionLocalOf<AppShapes> {
    error("No AppShapes set")
}
val LocalTypography = staticCompositionLocalOf<AppTypography> {
    error("No AppTypography set")
}
val LocalWindowInsets = staticCompositionLocalOf<LocalWindowInsetsData> {
    error("No LocalWindowInsetsData set")
}
