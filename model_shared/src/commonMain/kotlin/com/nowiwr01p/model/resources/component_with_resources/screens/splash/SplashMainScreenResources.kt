package com.nowiwr01p.model.resources.component_with_resources.screens.splash

import androidx.compose.runtime.Composable
import com.nowiwr01p.model.resources.component_with_resources.AppComponentWithResources
import com.nowiwr01p.model.resources.component_with_resources.screens.AppScreenWithTranslatedResources.SplashScreen

@Composable
fun SplashMainScreenResources(
    content: @Composable (resources: SplashScreenResources) -> Unit
) {
    AppComponentWithResources<SplashScreenResources>(
        component = SplashScreen,
        content = content
    )
}
