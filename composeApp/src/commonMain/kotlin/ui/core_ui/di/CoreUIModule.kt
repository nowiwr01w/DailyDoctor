package ui.core_ui.di

import org.kodein.di.DI
import org.kodein.di.bindSingleton
import ui.core_ui.statusbar.StatusBarColorHelper

val moduleCoreUI = DI.Module("CoreUIModule") {
    /**
     * STATUSBAR HELPER
     */
    bindSingleton { StatusBarColorHelper() }
}