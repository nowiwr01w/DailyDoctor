package base.theme

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
import currentPlatform
import model.color.AppColorsData
import model.color.ProvideCustomColors
import com.nowiwr01p.model.usecase.execute
import model.brand.AppBrand
import model.brand.AppBrand.*
import model.color.classic.AppClassicColors
import model.theme.AppTheme
import model.theme.AppTheme.*
import nowiwr01p.daily.doctor.app_presentation.theme.shape.AppShapes
import nowiwr01p.daily.doctor.app_presentation.theme.typography.AppTypography
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf
import ui.core_ui.helpers.window_insets.data.LocalWindowInsetsData
import ui.core_ui.helpers.window_insets.data.ProviderLocalWindowInsets
import usecase.brand.AppGetBrandUseCase
import usecase.theme.AppGetThemeModeUseCase

@Composable
fun AppTheme(
    shapes: AppShapes = koinInject { parametersOf(currentPlatform) },
    typography: AppTypography = koinInject { parametersOf(currentPlatform) },
    content: @Composable () -> Unit
) {
    val appThemedColors = getAppColors(
        theme = getAppTheme().value,
        brand = getAppBrand().value
    )

    ProviderLocalWindowInsets(insetsData = getWindowInsets().value) {
        ProvideCustomColors(colors = appThemedColors.value) {
            MaterialTheme(
                typography = typography.getTypography(FontFamily.Default), // TODO: Change dynamically
                shapes = shapes.shapes,
                content = content
            )
        }
    }
}

@Composable
private fun getAppBrand(
    getAppBrandUseCase: AppGetBrandUseCase = koinInject()
): State<AppBrand> {
    val appBrand: MutableState<AppBrand> = remember { mutableStateOf(AppBrandClassic()) }
    LaunchedEffect(Unit) {
        appBrand.value = getAppBrandUseCase.execute()
    }
    return appBrand
}

@Composable
private fun getAppTheme(
    appGetThemeModeUseCase: AppGetThemeModeUseCase = koinInject()
): State<AppTheme> {
    val appTheme = remember { mutableStateOf(LIGHT) }
    LaunchedEffect(Unit) {
        appGetThemeModeUseCase.execute().collect { themeMode ->
            appTheme.value = themeMode
        }
    }
    return appTheme
}

@Composable
private fun getAppColors(theme: AppTheme, brand: AppBrand): State<AppColorsData> {
    val appThemedColors = remember {
        mutableStateOf(AppClassicColors().appColorsLight)
    }
    appThemedColors.value = when (theme) {
        LIGHT -> brand.appColors.appColorsLight
        DARK -> brand.appColors.appColorsDark
    }
    return appThemedColors
}

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