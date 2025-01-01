package nowiwr01p.daily.doctor.new_resources.component_with_resources.screens

import nowiwr01p.daily.doctor.new_resources.component_with_resources.base.ComponentResources
import nowiwr01p.daily.doctor.new_resources.component_with_resources.base.ComponentTranslatedResources
import nowiwr01p.daily.doctor.new_resources.component_with_resources.base.ComponentWithTranslatedResources
import nowiwr01p.daily.doctor.new_resources.component_with_resources.screens.auth.AuthScreenResources
import nowiwr01p.daily.doctor.new_resources.component_with_resources.screens.auth.AuthScreenTranslatedResources
import nowiwr01p.daily.doctor.new_resources.component_with_resources.screens.splash.SplashScreenResources
import nowiwr01p.daily.doctor.new_resources.component_with_resources.screens.splash.SplashScreenTranslatedResources
import nowiwr01p.daily.doctor.new_resources.component_with_resources.screens.verification.VerificationScreenResources
import nowiwr01p.daily.doctor.new_resources.component_with_resources.screens.verification.VerificationScreenTranslatedResources

internal sealed class AppScreenWithTranslatedResources<T: ComponentResources>(
    override val resources: ComponentTranslatedResources<T>
): ComponentWithTranslatedResources<T>(resources) {
    /**
     * SPLASH
     */
    data object SplashScreen: AppScreenWithTranslatedResources<SplashScreenResources>(
        resources = SplashScreenTranslatedResources()
    )

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
}
