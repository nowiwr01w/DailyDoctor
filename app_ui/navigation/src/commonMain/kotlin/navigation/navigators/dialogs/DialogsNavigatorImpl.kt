package navigation.navigators.dialogs

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.nowiwr01p.model.coroutines.app_scope.AppScope
import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import constants.COMPONENT_TRANSITION_ANIMATION_DURATION
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import navigation.config.config.DialogsNavigationConfig
import navigation.config.config.DialogsNavigationConfig.ExitApp
import navigation.config.config.DialogsNavigationConfig.SelectLanguage
import navigation.navigators.getDefaultBackCallback
import navigation.navigators.screen_results.ScreenResultHandler
import view_model.BaseViewModelComponent

class DialogsNavigatorImpl(
    appNavigationContext: ComponentContext,
    private val appScope: AppScope,
    private val dispatchers: AppDispatchers,
    private val navigation: SlotNavigation<DialogsNavigationConfig>,
    private val screenResultHandler: ScreenResultHandler
): DialogsNavigator, ComponentContext by appNavigationContext {
    /**
     * CONFIG
     */
    override val dialogsChildSlot = childSlot(
        key = "DialogsChildSlot",
        source = navigation,
        serializer = DialogsNavigationConfig.serializer(),
        handleBackButton = true,
        childFactory = { config, childContext ->
            config.child.apply {
                resultHandler = screenResultHandler
                baseComponent = BaseViewModelComponent(childContext).apply {
                    backHandler.register(getDefaultBackCallback(isCancellable))
                }
            }
        }
    )

    /**
     * BACK NAVIGATION
     */
    override lateinit var hideDialogCallback: () -> Unit

    override fun navigateBack(onComplete: (isSuccess: Boolean) -> Unit) {
        appScope.scope.launch(dispatchers.main) {
            hideDialogCallback()
            delay(COMPONENT_TRANSITION_ANIMATION_DURATION.toLong())
            navigation.dismiss(onComplete = onComplete)
        }
    }
    /**
     * DIALOGS
     */
    override fun showExitAppDialog() {
        navigation.activate(ExitApp)
    }

    override fun showSelectLanguageDialog(isFirstSelection: Boolean) {
        navigation.activate(SelectLanguage(isFirstSelection))
    }
}
