package ui.core_ui.di

import com.nowiwr01p.model.coroutines.app_scope.AppScope
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import ui.core_ui.helpers.snack_bar.SnackBarHelper
import ui.core_ui.helpers.window_insets.AppWindowColorsHelper

val moduleCoreUI = DI.Module("CoreUIModule") {
    /**
     * CHANGE APP WINDOW COLORS HELPER
     */
    bindSingleton {
        AppWindowColorsHelper(appScope = instance<AppScope>())
    }
    /**
     * SHOW SNACK BAR WITH CUSTOM PARAMS
     */
    bindSingleton {
        SnackBarHelper(appScope = instance<AppScope>())
    }
}