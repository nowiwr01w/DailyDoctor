package ui.core_ui.di

import domain.coroutines.app_scope.AppScope
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import ui.core_ui.statusbar.StatusBarColorHelper

val moduleCoreUI = DI.Module("CoreUIModule") {
    /**
     * STATUS BAR COLOR HELPER
     */
    bindSingleton {
        StatusBarColorHelper(appScope = instance<AppScope>())
    }
}