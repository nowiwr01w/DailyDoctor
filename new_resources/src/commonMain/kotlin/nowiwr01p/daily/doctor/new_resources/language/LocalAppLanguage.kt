package nowiwr01p.daily.doctor.new_resources.language

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
