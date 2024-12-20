package nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.config

import kotlinx.serialization.Serializable
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.child.MobileScreensChild
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.child.MobileScreensChild.AuthChild
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.child.MobileScreensChild.HomeChild
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.child.MobileScreensChild.OnboardingChild
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.child.MobileScreensChild.PinCodeChild
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.child.MobileScreensChild.SplashChild
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.child.MobileScreensChild.SubscriptionChild
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.child.MobileScreensChild.VerificationChild
import nowiwr01p.daily.doctor.app_presentation.navigation.model.pin.PinCodeMode

@Serializable
sealed class MobileScreensConfig(val child: MobileScreensChild) {
    @Serializable
    data object Splash: MobileScreensConfig(
        child = SplashChild
    )
    @Serializable
    data object Onboarding: MobileScreensConfig(
        child = OnboardingChild
    )
    @Serializable
    data object Auth: MobileScreensConfig(
        child = AuthChild
    )
    @Serializable
    data class Verification(val phone: String, val verificationToken: String): MobileScreensConfig(
        child = VerificationChild(phone, verificationToken)
    )
    @Serializable
    data class PinCode(val pinCodeMode: PinCodeMode): MobileScreensConfig(
        child = PinCodeChild(pinCodeMode)
    )
    @Serializable
    data object Subscription: MobileScreensConfig(
        child = SubscriptionChild
    )
    @Serializable
    data object Home: MobileScreensConfig(
        child = HomeChild
    )
}
