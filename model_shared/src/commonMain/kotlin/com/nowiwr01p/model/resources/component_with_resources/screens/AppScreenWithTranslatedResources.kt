package com.nowiwr01p.model.resources.component_with_resources.screens

import com.nowiwr01p.model.resources.component_with_resources.base.ComponentResources
import com.nowiwr01p.model.resources.component_with_resources.base.ComponentTranslatedResources
import com.nowiwr01p.model.resources.component_with_resources.base.ComponentWithTranslatedResources
import com.nowiwr01p.model.resources.component_with_resources.screens.auth.AuthScreenResources
import com.nowiwr01p.model.resources.component_with_resources.screens.auth.AuthScreenTranslatedResources
import com.nowiwr01p.model.resources.component_with_resources.screens.pin.PinScreenResources
import com.nowiwr01p.model.resources.component_with_resources.screens.pin.PinScreenTranslatedResources
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.screen_info.SubscriptionScreenResources
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.screen_info.SubscriptionScreenTranslatedResources
import com.nowiwr01p.model.resources.component_with_resources.screens.verification.VerificationScreenResources
import com.nowiwr01p.model.resources.component_with_resources.screens.verification.VerificationScreenTranslatedResources

internal sealed class AppScreenWithTranslatedResources<T: ComponentResources>(
    override val resources: ComponentTranslatedResources<T>
): ComponentWithTranslatedResources<T>(resources) {
    /**
     * AUTH
     */
    data object AuthScreen: AppScreenWithTranslatedResources<AuthScreenResources>(
        resources = AuthScreenTranslatedResources()
    )

    /**
     * VERIFICATION
     */
    data object VerificationScreen: AppScreenWithTranslatedResources<VerificationScreenResources>(
        resources = VerificationScreenTranslatedResources()
    )
    /**
     * PIN
     */
    data object PinScreen: AppScreenWithTranslatedResources<PinScreenResources>(
        resources = PinScreenTranslatedResources()
    )
    /**
     * SUBSCRIPTION
     */
    data object SubscriptionScreen: AppScreenWithTranslatedResources<SubscriptionScreenResources>(
        resources = SubscriptionScreenTranslatedResources()
    )
}
