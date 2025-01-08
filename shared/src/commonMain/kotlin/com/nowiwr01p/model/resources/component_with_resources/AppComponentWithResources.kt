package com.nowiwr01p.model.resources.component_with_resources

import androidx.compose.runtime.Composable
import com.nowiwr01p.model.resources.component_with_resources.base.ComponentResources
import com.nowiwr01p.model.resources.component_with_resources.base.ComponentWithTranslatedResources
import com.nowiwr01p.model.resources.language.Language

@Composable
internal inline fun <reified T: ComponentResources> AppComponentWithResources(
    component: ComponentWithTranslatedResources<T>,
    language: Language? = null,
    content: @Composable (T) -> Unit
) {
    val resources = component.resources.getResourcesByLanguage(language)
    content(resources)
}