package ui.core_ui.di

import domain.coroutines.app_scope.AppScope
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import ui.core_ui.window_insets.AppWindowColorsHelper

val moduleCoreUI = DI.Module("CoreUIModule") {
    /**
     * CHANGE APP WINDOW COLORS HELPER
     */
    bindSingleton {
        AppWindowColorsHelper(appScope = instance<AppScope>())
    }
}