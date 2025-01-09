package nowiwr01p.daily.doctor.app_ui.navigation.mobile.navigation.config.child

import com.arkivanov.decompose.extensions.compose.stack.animation.StackAnimator
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import kotlinx.serialization.Serializable
import navigation.config.BaseNavigationChild
import navigation.navigators.screen_results.ScreenResultHandler
import nowiwr01p.daily.doctor.app_ui.navigation.model.pin.PinCodeMode
import view_model.BaseViewModelComponent

@Serializable
sealed class MobileScreensChild(
    val animation: StackAnimator = slide()
): BaseNavigationChild {
    /**
     * COMPONENT
     */
    override lateinit var baseComponent: BaseViewModelComponent

    /**
     * SCREEN RESULT HANDLER
     */
    override lateinit var resultHandler: ScreenResultHandler

    /**
     * SCREENS
     */
    @Serializable
    data object SplashChild: MobileScreensChild()

    @Serializable
    data object OnboardingChild: MobileScreensChild()

    @Serializable
    data object AuthChild: MobileScreensChild()

    @Serializable
    data class VerificationChild(
        val phone: String,
        val verificationToken: String
    ): MobileScreensChild()

    @Serializable
    data class PinCodeChild(
        val pinCodeMode: PinCodeMode
    ): MobileScreensChild()

    @Serializable
    data object SubscriptionChild: MobileScreensChild()

    @Serializable
    data object HomeChild: MobileScreensChild()
}
