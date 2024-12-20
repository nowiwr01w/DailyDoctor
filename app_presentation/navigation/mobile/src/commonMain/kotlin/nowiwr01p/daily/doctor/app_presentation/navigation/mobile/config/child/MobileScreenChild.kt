package nowiwr01p.daily.doctor.app_presentation.navigation.mobile.config.child

import com.arkivanov.decompose.extensions.compose.stack.animation.StackAnimator
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import kotlinx.serialization.Serializable
import navigation.config.BaseNavigationChild
import nowiwr01p.daily.doctor.app_presentation.navigation.pin_code.model.PinCodeMode
import view_model.BaseViewModelComponent

@Serializable
sealed class MobileScreenChild(
    val animation: StackAnimator = slide()
): BaseNavigationChild {
    /**
     * COMPONENT
     */
    override lateinit var baseComponent: BaseViewModelComponent
    /**
     * SCREENS
     */
    @Serializable
    data object SplashChild: MobileScreenChild()

    @Serializable
    data object OnboardingChild: MobileScreenChild()

    @Serializable
    data object AuthChild: MobileScreenChild()

    @Serializable
    data class VerificationChild(
        val phone: String,
        val verificationToken: String
    ): MobileScreenChild()

    @Serializable
    data class PinCodeChild(
        val pinCodeMode: PinCodeMode
    ): MobileScreenChild()

    @Serializable
    data object SubscriptionChild: MobileScreenChild()

    @Serializable
    data object HomeChild: MobileScreenChild()
}
