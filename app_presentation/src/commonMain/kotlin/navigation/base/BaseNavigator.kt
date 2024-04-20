package navigation.base

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import navigation.MainNavigatorImpl.AppNavigationConfig

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