package com.nowiwr01p.model.resources.component_with_resources.screens.auth

import androidx.compose.runtime.Composable
import com.nowiwr01p.model.resources.component_with_resources.AppComponentWithResources
import com.nowiwr01p.model.resources.component_with_resources.screens.AppScreenWithTranslatedResources.AuthScreen

@Composable
fun AuthMainScreenResources(
    content: @Composable (resources: AuthScreenResources) -> Unit
) {
    AppComponentWithResources<AuthScreenResources>(
        component = AuthScreen,
        content = content
    )
}