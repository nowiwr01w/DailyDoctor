package com.nowiwr01p.model.model.app_config.settings.app

import com.nowiwr01p.model.model.app_config.AppColorThemeType
import com.nowiwr01p.model.model.app_config.config.BrandConfigType
import kotlinx.serialization.Serializable

@Serializable
data class BrandSettings(
    val brandConfigType: BrandConfigType,
    val availableColorThemes: List<AppColorThemeType>,
    val isOnboardingEnabled: Boolean
)