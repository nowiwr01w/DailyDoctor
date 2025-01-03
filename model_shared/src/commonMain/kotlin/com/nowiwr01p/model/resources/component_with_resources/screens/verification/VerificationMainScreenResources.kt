package com.nowiwr01p.model.resources.component_with_resources.screens.verification

import androidx.compose.runtime.Composable
import com.nowiwr01p.model.resources.component_with_resources.AppComponentWithResources
import com.nowiwr01p.model.resources.component_with_resources.screens.AppScreenWithTranslatedResources

@Composable
fun VerificationMainScreenResources(
    content: @Composable (resources: VerificationScreenResources) -> Unit
) {
    AppComponentWithResources<VerificationScreenResources>(
        component = AppScreenWithTranslatedResources.VerificationScreen,
        content = content
    )
}