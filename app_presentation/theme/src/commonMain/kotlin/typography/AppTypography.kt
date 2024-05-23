package nowiwr01p.daily.doctor.app_presentation.theme.typography

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.FontFamily

interface AppTypography {
    fun getTypography(fontFamily: FontFamily): Typography
}