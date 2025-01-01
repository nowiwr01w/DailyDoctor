package nowiwr01p.daily.doctor.new_resources.component_with_resources.screens.splash

import androidx.compose.runtime.Composable
import nowiwr01p.daily.doctor.new_resources.component_with_resources.AppComponentWithResources
import nowiwr01p.daily.doctor.new_resources.component_with_resources.screens.AppScreenWithTranslatedResources.SplashScreen

@Composable
fun SplashMainScreenResources(
    content: @Composable (resources: SplashScreenResources) -> Unit
) {
    AppComponentWithResources<SplashScreenResources>(
        component = SplashScreen,
        content = content
    )
}
