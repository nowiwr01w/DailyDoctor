package base.theme.typography

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.FontFamily

interface AppTypography {
    fun getTypography(fontFamily: FontFamily): Typography
}