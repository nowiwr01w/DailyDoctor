package com.nowiwr01p.model.resources.component_with_resources.screens.pin

import androidx.compose.runtime.Composable
import com.nowiwr01p.model.resources.component_with_resources.AppComponentWithResources
import com.nowiwr01p.model.resources.component_with_resources.screens.AppScreenWithTranslatedResources

@Composable
fun PinMainScreenResources(
    content: @Composable (resources: PinScreenResources) -> Unit
) {
    AppComponentWithResources<PinScreenResources>(
        component = AppScreenWithTranslatedResources.PinScreen,
        content = content
    )
}