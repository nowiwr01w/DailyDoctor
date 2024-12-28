package theme.typography

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily

interface AppTypography {
    val displayLarge: TextStyle
    val displayMedium: TextStyle
    val displaySmall: TextStyle
    val headlineLarge: TextStyle
    val headlineMedium: TextStyle
    val headlineSmall: TextStyle
    val titleLarge: TextStyle
    val titleMedium: TextStyle
    val titleSmall: TextStyle
    val bodyLarge: TextStyle
    val bodyMedium: TextStyle
    val bodySmall: TextStyle
    val labelLarge: TextStyle
    val labelMedium: TextStyle
    val labelSmall: TextStyle
    var fontFamily: FontFamily
}
