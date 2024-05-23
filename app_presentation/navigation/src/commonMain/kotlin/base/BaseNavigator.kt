package nowiwr01p.daily.doctor.app_presentation.navigation.base

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigatorImpl.AppNavigationConfig

abstract class BaseNavigator {
    /**
     * NAVIGATION STACK
     */
    abstract val navigation: StackNavigation<AppNavigationConfig>

    /**
     * SET CHILD CONTEXT
     */
    protected lateinit var childContext: ComponentContext

    fun updateChildContext(context: ComponentContext) {
        childContext = context
    }
}