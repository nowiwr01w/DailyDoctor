package nowiwr01p.daily.doctor.new_resources.component_with_resources.base

import androidx.compose.runtime.Composable
import nowiwr01p.daily.doctor.new_resources.language.Language.Georgian
import nowiwr01p.daily.doctor.new_resources.language.Language.Russian
import nowiwr01p.daily.doctor.new_resources.language.Language.Ukrainian
import nowiwr01p.daily.doctor.new_resources.language.LocalAppLanguage

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
    fun getResourcesByLanguage() = when (LocalAppLanguage.current) {
        is Georgian -> getGeorgianResources()
        is Russian -> getRussianResources()
        is Ukrainian -> getUkrainianResources()
        else -> getEnglishResources()
    }
}
