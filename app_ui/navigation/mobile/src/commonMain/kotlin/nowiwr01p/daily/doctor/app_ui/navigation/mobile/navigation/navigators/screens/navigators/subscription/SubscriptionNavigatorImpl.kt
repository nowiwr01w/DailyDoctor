package nowiwr01p.daily.doctor.app_ui.navigation.mobile.navigation.navigators.screens.navigators.subscription

import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.replaceAll
import nowiwr01p.daily.doctor.app_ui.navigation.mobile.navigation.config.config.MobileScreensConfig
import nowiwr01p.daily.doctor.app_ui.navigation.mobile.navigation.config.config.MobileScreensConfig.Subscription

class SubscriptionNavigatorImpl(
    override val navigation: StackNavigation<MobileScreensConfig>
): SubscriptionNavigator {

    override fun navigateToSubscription() {
        navigation.replaceAll(Subscription) // TODO: Don't use replaceAll() if it's not from Auth flow
    }
}
