package ui.core_ui.di

import com.nowiwr01p.model.coroutines.app_scope.AppScope
import org.koin.dsl.module
import ui.core_ui.helpers.snack_bar.SnackBarHelper
import ui.core_ui.helpers.window_insets.AppWindowColorsHelper

val moduleCoreUI = module {
    /**
     * CHANGE APP WINDOW COLORS HELPER
     */
    single {
        AppWindowColorsHelper(appScope = get<AppScope>())
    }
    /**
     * SHOW SNACK BAR WITH CUSTOM PARAMS
     */
    single {
        SnackBarHelper(appScope = get<AppScope>())
    }
}