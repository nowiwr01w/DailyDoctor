package nowiwr01p.daily.doctor.new_resources.component_with_resources.screens.auth

import androidx.compose.runtime.Composable
import nowiwr01p.daily.doctor.new_resources.component_with_resources.AppComponentWithResources
import nowiwr01p.daily.doctor.new_resources.component_with_resources.screens.AppScreenWithTranslatedResources.AuthScreen

@Composable
fun AuthMainScreenResources(
    content: @Composable (resources: AuthScreenResources) -> Unit
) {
    AppComponentWithResources<AuthScreenResources>(
        component = AuthScreen,
        content = content
    )
}