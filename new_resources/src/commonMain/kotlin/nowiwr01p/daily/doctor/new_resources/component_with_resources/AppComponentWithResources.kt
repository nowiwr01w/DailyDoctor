package nowiwr01p.daily.doctor.new_resources.component_with_resources

import androidx.compose.runtime.Composable
import nowiwr01p.daily.doctor.new_resources.component_with_resources.base.ComponentResources
import nowiwr01p.daily.doctor.new_resources.component_with_resources.base.ComponentWithTranslatedResources

@Composable
internal inline fun <reified T: ComponentResources> AppComponentWithResources(
    component: ComponentWithTranslatedResources<T>,
    content: @Composable (T) -> Unit
) {
    val resources = component.resources.getResourcesByLanguage()
    content(resources)
}