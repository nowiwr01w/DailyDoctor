package navigation.auth

import com.arkivanov.decompose.ComponentContext

class AuthNavigatorImpl(
    context: ComponentContext,
    private val navigateToAuthCallback: () -> Unit
): AuthNavigator {

    override fun navigateToAuth() {
        navigateToAuthCallback()
    }
}