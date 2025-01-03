package com.nowiwr01p.model.resources.component_with_resources.screens.splash

import com.nowiwr01p.model.resources.component_with_resources.base.ComponentResources
import com.nowiwr01p.model.resources.component_with_resources.base.ComponentTranslatedResources

/**
 * DATA
 */
sealed interface SplashScreenResources: ComponentResources {
    val appName: String
}

/**
 * TRANSLATIONS
 */
internal data class SplashScreenResourcesGeorgian(
    override val appName: String = "დაურეკეთ ექიმს"
): SplashScreenResources

internal data class SplashScreenResourcesEnglish(
    override val appName: String = "Call Doctor"
): SplashScreenResources

internal data class SplashScreenResourcesRussian(
    override val appName: String = "Звоните Доктору"
): SplashScreenResources

internal data class SplashScreenResourcesUkrainian(
    override val appName: String = "Телефонуйте Лікарю"
): SplashScreenResources

/**
 * SCREEN RESOURCES
 */
internal class SplashScreenTranslatedResources: ComponentTranslatedResources<SplashScreenResources>() {
    override fun getGeorgianResources() = SplashScreenResourcesGeorgian()
    override fun getEnglishResources() = SplashScreenResourcesEnglish()
    override fun getRussianResources() = SplashScreenResourcesRussian()
    override fun getUkrainianResources() = SplashScreenResourcesUkrainian()
}

