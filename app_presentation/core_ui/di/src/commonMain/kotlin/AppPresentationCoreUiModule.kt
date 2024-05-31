import com.nowiwr01p.model.coroutines.app_scope.AppScope
import org.koin.dsl.module
import helpers.snack_bar.SnackBarHelper

val moduleAppPresentationCoreUi = module {
    /**
     * SHOW SNACK BAR WITH CUSTOM PARAMS
     */
    single {
        SnackBarHelper(appScope = get<AppScope>())
    }
}