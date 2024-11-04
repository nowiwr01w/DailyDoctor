package theme

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import components.LocalWindowInsetsData
import components.ProviderLocalWindowInsets
import model.color.AppColorTheme
import model.color.classic.AppClassicColorThemeLight
import model.color.data.ProvideCustomColors
import org.koin.compose.koinInject
import org.koin.core.qualifier.named
import platform.currentPlatform
import theme.shape.AppShapes
import theme.typography.AppTypography

@Composable
fun AppTheme(
    appColorTheme: AppColorTheme,
    shapes: AppShapes = koinInject(named(currentPlatform)),
    typography: AppTypography = koinInject(named(currentPlatform)),
    content: @Composable () -> Unit
) {
    ProviderLocalWindowInsets(insetsData = getWindowInsets().value) {
        ProvideCustomColors(colors = appColorTheme.appColorsData) {
            MaterialTheme(
                typography = typography.getTypography(FontFamily.Default), // TODO: Change dynamically
                shapes = shapes.shapes,
                content = content
            )
        }
    }
}

/**
 * SHOULD BE USED ONLY FOR @Preview PURPOSE
 */
@Composable
fun AppThemePreview(
    shapes: AppShapes = koinInject(named(currentPlatform)),
    typography: AppTypography = koinInject(named(currentPlatform)),
    content: @Composable () -> Unit
) {
    ProviderLocalWindowInsets(insetsData = getWindowInsets().value) {
        ProvideCustomColors(colors = AppClassicColorThemeLight().appColorsData) {
            MaterialTheme(
                typography = typography.getTypography(FontFamily.Default), // TODO: Change dynamically
                shapes = shapes.shapes,
                content = content
            )
        }
    }
}

/**
 * WINDOW INSETS
 */
@Composable
private fun getWindowInsets(): State<LocalWindowInsetsData> {
    val data = with(LocalDensity.current) {
        LocalWindowInsetsData(
            appTopPadding = WindowInsets.statusBars.getTop(this).toDp(),
            appBottomPadding = WindowInsets.navigationBars.getBottom(this).toDp()
        )
    }
    return derivedStateOf { data }
}