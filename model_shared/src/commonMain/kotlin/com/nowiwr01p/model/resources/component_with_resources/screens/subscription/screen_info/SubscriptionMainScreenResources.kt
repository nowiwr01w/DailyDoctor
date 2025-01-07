package com.nowiwr01p.model.resources.component_with_resources.screens.subscription.screen_info

import androidx.compose.runtime.Composable
import com.nowiwr01p.model.resources.component_with_resources.AppComponentWithResources
import com.nowiwr01p.model.resources.component_with_resources.screens.AppScreenWithTranslatedResources.SubscriptionScreen

@Composable
fun SubscriptionMainScreenResources(
    content: @Composable (resources: SubscriptionScreenResources) -> Unit
) {
    AppComponentWithResources<SubscriptionScreenResources>(
        component = SubscriptionScreen,
        content = content
    )
}