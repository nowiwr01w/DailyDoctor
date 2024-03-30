package navigation.splash

import com.arkivanov.decompose.ComponentContext

class SplashNavigatorImpl(
    context: ComponentContext,
    private val navigateToSpashCallback: () -> Unit
): SplashNavigator {

    override fun navigateToSplash() {
        navigateToSpashCallback()
    }
}