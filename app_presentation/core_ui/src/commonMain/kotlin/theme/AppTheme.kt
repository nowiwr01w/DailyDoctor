package theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import model.color.AppColorTheme

@Composable
fun AppTheme(
    appColorTheme: AppColorTheme,
    appFontFamily: FontFamily = FontFamily.Default,
    content: @Composable () -> Unit
) {
    ProvideCustomTheme(
        appColorTheme = appColorTheme,
        appFontFamily = appFontFamily,
        content = content
    )
}
