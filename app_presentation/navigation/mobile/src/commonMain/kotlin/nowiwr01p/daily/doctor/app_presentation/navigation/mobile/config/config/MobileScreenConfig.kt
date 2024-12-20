package nowiwr01p.daily.doctor.app_presentation.navigation.mobile.config.config

import kotlinx.serialization.Serializable
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.config.child.MobileScreenChild
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.config.child.MobileScreenChild.SplashChild
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.config.child.MobileScreenChild.OnboardingChild
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.config.child.MobileScreenChild.AuthChild
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.config.child.MobileScreenChild.VerificationChild
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.config.child.MobileScreenChild.PinCodeChild
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.config.child.MobileScreenChild.SubscriptionChild
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.config.child.MobileScreenChild.HomeChild
import nowiwr01p.daily.doctor.app_presentation.navigation.pin_code.model.PinCodeMode

@Serializable
sealed class MobileScreenConfig(val child: MobileScreenChild) {
    @Serializable
    data object Splash: MobileScreenConfig(
        child = SplashChild
    )
    @Serializable
    data object Onboarding: MobileScreenConfig(
        child = OnboardingChild
    )
    @Serializable
    data object Auth: MobileScreenConfig(
        child = AuthChild
    )
    @Serializable
    data class Verification(val phone: String, val verificationToken: String): MobileScreenConfig(
        child = VerificationChild(phone, verificationToken)
    )
    @Serializable
    data class PinCode(val pinCodeMode: PinCodeMode): MobileScreenConfig(
        child = PinCodeChild(pinCodeMode)
    )
    @Serializable
    data object Subscription: MobileScreenConfig(
        child = SubscriptionChild
    )
    @Serializable
    data object Home: MobileScreenConfig(
        child = HomeChild
    )
}
