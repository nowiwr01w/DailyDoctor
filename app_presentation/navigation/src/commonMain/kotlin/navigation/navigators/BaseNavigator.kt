package navigation.navigators

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.backhandler.BackCallback

interface BaseNavigator: ComponentContext {
    fun navigateBack(onComplete: (isSuccess: Boolean) -> Unit = {})
}

fun BaseNavigator.getDefaultBackCallback(allowBackNavigation: Boolean) = BackCallback(
    isEnabled = true,
    onBack = {
        if (allowBackNavigation) {
            navigateBack()
        }
    }
)
