package com.nowiwr01p.model.resources.language

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

val LocalAppLanguage = staticCompositionLocalOf<Language> {
    error("No AppLanguage set")
}

@Composable
fun AppWithLanguage(
    appLanguage: Language,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalAppLanguage provides appLanguage) {
        content()
    }
}
