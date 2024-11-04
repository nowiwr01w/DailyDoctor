package theme

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import com.nowiwr01p.model.usecase.execute
import components.LocalWindowInsetsData
import components.ProviderLocalWindowInsets
import model.color.AppColorTheme
import model.color.allAppColorThemes
import model.color.classic.AppClassicColorThemeLight
import model.color.data.ProvideCustomColors
import org.koin.compose.koinInject
import org.koin.core.qualifier.named
import platform.currentPlatform
import theme.shape.AppShapes
import theme.typography.AppTypography
import usecase.brand_config.AppGetBrandConfigUseCase

@Composable
fun AppTheme(
    shapes: AppShapes = koinInject(named(currentPlatform)),
    typography: AppTypography = koinInject(named(currentPlatform)),
    content: @Composable () -> Unit
) {
    ProviderLocalWindowInsets(insetsData = getWindowInsets().value) {
        ProvideCustomColors(colors = getAppColorTheme().value.appColorsData) {
            MaterialTheme(
                typography = typography.getTypography(FontFamily.Default), // TODO: Change dynamically
                shapes = shapes.shapes,
                content = content
            )
        }
    }
}

/**
 * APP CONFIG
 */
@Composable
private fun getAppColorTheme(
    appGetBrandConfigUseCase: AppGetBrandConfigUseCase = koinInject()
): State<AppColorTheme> {
    val appColorTheme: MutableState<AppColorTheme> = remember {
        mutableStateOf(AppClassicColorThemeLight())
    }
    LaunchedEffect(Unit) {
        val appConfig = appGetBrandConfigUseCase.execute()
        val defaultAppThemeType = appConfig.brandSettings.availableColorThemes.first()
        appColorTheme.value = allAppColorThemes
            .find { it.type == defaultAppThemeType } ?: AppClassicColorThemeLight()
    }
    return appColorTheme
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