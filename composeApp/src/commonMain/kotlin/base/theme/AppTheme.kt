package base.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontFamily
import base.theme.color.classic.AppClassicColors
import base.theme.color.data.ProvideCustomColors
import base.theme.shape.AppShapes
import base.theme.typography.AppTypography
import currentPlatform
import domain.repository.AppTheme.DARK
import domain.repository.AppTheme.LIGHT
import domain.usecase.app_theme.GetAppThemeModeUseCase
import domain.usecase.execute
import org.kodein.di.compose.rememberInstance

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    val shapes by rememberInstance<AppShapes>(currentPlatform)
    val typography by rememberInstance<AppTypography>(currentPlatform)
    val getAppThemeModeUseCase by rememberInstance<GetAppThemeModeUseCase>()

    var appTheme by remember { mutableStateOf(LIGHT) }
    LaunchedEffect(Unit) {
        getAppThemeModeUseCase.execute().collect { themeMode ->
            appTheme = themeMode
        }
    }

    val appColorsByBrand = AppClassicColors()

    val appThemedColors = when (appTheme) {
        LIGHT -> appColorsByBrand.appColorsLight
        DARK -> appColorsByBrand.appColorsDark
    }

    ProvideCustomColors(colors = appThemedColors) {
        MaterialTheme(
            typography = typography.getTypography(FontFamily.Default), // TODO: Change dynamically
            shapes = shapes.shapes,
            content = content
        )
    }
}