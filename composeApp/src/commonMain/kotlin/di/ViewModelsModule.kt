package di

import kotlinx.coroutines.CoroutineScope
import org.kodein.di.DI
import org.kodein.di.bindFactory
import ui.common.home.HomeViewModel

val moduleViewModels = DI.Module("ViewModelsModule") {
    /**
     * HOME
     */
    bindFactory<CoroutineScope, HomeViewModel> { scope ->
        HomeViewModel(scope)
    }
}