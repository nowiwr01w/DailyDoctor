package navigation.config

import navigation.navigators.screen_results.ScreenResultHandler
import view_model.BaseViewModelComponent

interface BaseNavigationChild {
    val baseComponent: BaseViewModelComponent
    val resultHandler: ScreenResultHandler
}
