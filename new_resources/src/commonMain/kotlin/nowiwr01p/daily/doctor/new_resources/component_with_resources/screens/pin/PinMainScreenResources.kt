package nowiwr01p.daily.doctor.new_resources.component_with_resources.screens.pin

import androidx.compose.runtime.Composable
import nowiwr01p.daily.doctor.new_resources.component_with_resources.AppComponentWithResources
import nowiwr01p.daily.doctor.new_resources.component_with_resources.screens.AppScreenWithTranslatedResources

@Composable
fun PinMainScreenResources(
    content: @Composable (resources: PinScreenResources) -> Unit
) {
    AppComponentWithResources<PinScreenResources>(
        component = AppScreenWithTranslatedResources.PinScreen,
        content = content
    )
}