package navigation.navigators.screens

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import navigation.config.child.ScreenChild
import navigation.config.config.ScreenNavigationConfig
import navigation.navigators.BaseNavigator

interface ScreensNavigator: BaseNavigator {
    /** CONFIG **/
    val appScreensChildStack: Value<ChildStack<ScreenNavigationConfig, ScreenChild>>
}
