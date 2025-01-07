package com.nowiwr01p.model.resources.component_with_resources.base

import androidx.compose.runtime.Composable
import com.nowiwr01p.model.resources.language.Language
import com.nowiwr01p.model.resources.language.Language.Georgian
import com.nowiwr01p.model.resources.language.Language.Russian
import com.nowiwr01p.model.resources.language.Language.Ukrainian
import com.nowiwr01p.model.resources.language.LocalAppLanguage

interface ComponentResources

internal abstract class ComponentTranslatedResources<T: ComponentResources> {
    /**
     * TRANSLATIONS
     */
    abstract fun getGeorgianResources(): T
    abstract fun getEnglishResources(): T
    abstract fun getRussianResources(): T
    abstract fun getUkrainianResources(): T

    /**
     * RESOURCES
     */
    @Composable
    fun getResourcesByLanguage(language: Language?): T {
        val appLanguage = language ?: LocalAppLanguage.current
        return when (appLanguage) {
            is Georgian -> getGeorgianResources()
            is Russian -> getRussianResources()
            is Ukrainian -> getUkrainianResources()
            else -> getEnglishResources()
        }
    }
}
