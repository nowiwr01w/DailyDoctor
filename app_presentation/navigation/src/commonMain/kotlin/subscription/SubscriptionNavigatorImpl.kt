package nowiwr01p.daily.doctor.app_presentation.navigation.subscription

import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.pushNew
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigatorImpl.AppNavigationConfig
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigatorImpl.AppNavigationConfig.Subscription

class SubscriptionNavigatorImpl(
    override val navigation: StackNavigation<AppNavigationConfig>
): SubscriptionNavigator() {

    @OptIn(ExperimentalDecomposeApi::class)
    override fun navigateToSubscription() {
        navigation.pushNew(Subscription)
    }
}