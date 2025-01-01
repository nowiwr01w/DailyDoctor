package nowiwr01p.daily.doctor.new_resources.component_with_resources.screens.verification

import androidx.compose.runtime.Composable
import nowiwr01p.daily.doctor.new_resources.component_with_resources.AppComponentWithResources
import nowiwr01p.daily.doctor.new_resources.component_with_resources.screens.AppScreenWithTranslatedResources

@Composable
fun VerificationMainScreenResources(
    content: @Composable (resources: VerificationScreenResources) -> Unit
) {
    AppComponentWithResources<VerificationScreenResources>(
        component = AppScreenWithTranslatedResources.VerificationScreen,
        content = content
    )
}