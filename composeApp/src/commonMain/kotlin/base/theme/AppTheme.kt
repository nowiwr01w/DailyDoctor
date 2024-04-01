package base.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.FontFamily
import base.theme.shape.AppShapes
import base.theme.typography.AppTypography
import currentPlatform
import domain.model.color.AppColorsData
import domain.model.color.ProvideCustomColors
import domain.model.color.classic.AppClassicColors
import domain.repository.brand.AppBrand
import domain.repository.brand.AppBrand.AppBrandClassic
import domain.repository.theme.AppTheme
import domain.repository.theme.AppTheme.DARK
import domain.repository.theme.AppTheme.LIGHT
import domain.usecase.brand.GetAppBrandUseCase
import domain.usecase.execute
import domain.usecase.theme.GetAppThemeModeUseCase
import org.kodein.di.compose.rememberInstance

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    val shapes by rememberInstance<AppShapes>(currentPlatform)
    val typography by rememberInstance<AppTypography>(currentPlatform)

    val appThemedColors = getAppColors(
        theme = getAppTheme().value,
        brand = getAppBrand().value
    )

    ProvideCustomColors(colors = appThemedColors.value) {
        MaterialTheme(
            typography = typography.getTypography(FontFamily.Default), // TODO: Change dynamically
            shapes = shapes.shapes,
            content = content
        )
    }
}

@Composable
private fun getAppBrand(): State<AppBrand> {
    val getAppBrandUseCase by rememberInstance<GetAppBrandUseCase>()
    val appBrand: MutableState<AppBrand> = remember { mutableStateOf(AppBrandClassic()) }
    LaunchedEffect(Unit) {
        appBrand.value = getAppBrandUseCase.execute()
    }
    return appBrand
}

@Composable
private fun getAppTheme(): State<AppTheme> {
    val getAppThemeModeUseCase by rememberInstance<GetAppThemeModeUseCase>()
    val appTheme = remember { mutableStateOf(LIGHT) }
    LaunchedEffect(Unit) {
        getAppThemeModeUseCase.execute().collect { themeMode ->
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