package base.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import base.theme.shape.AppShapes
import base.theme.typography.AppTypography
import currentPlatform
import org.kodein.di.compose.rememberInstance

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    val shapes by rememberInstance<AppShapes>(currentPlatform)
    val typography by rememberInstance<AppTypography>(currentPlatform)
    MaterialTheme(
        typography = typography.getTypography(FontFamily.Default), // TODO: Change dynamically
        shapes = shapes.shapes,
        content = content
    )
}