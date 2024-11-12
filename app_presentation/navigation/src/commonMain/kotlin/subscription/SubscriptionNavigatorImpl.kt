package nowiwr01p.daily.doctor.app_presentation.navigation.subscription

import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.replaceAll
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigatorImpl.AppNavigationConfig
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigatorImpl.AppNavigationConfig.Subscription

class SubscriptionNavigatorImpl(
    override val navigation: StackNavigation<AppNavigationConfig>
): SubscriptionNavigator() {

    override fun navigateToSubscription() {
        navigation.replaceAll(Subscription) // TODO: Don't use replaceAll() if it's not from Auth flow
    }
}