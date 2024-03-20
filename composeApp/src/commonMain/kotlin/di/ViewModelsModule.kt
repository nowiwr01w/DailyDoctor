package di

import kotlinx.coroutines.CoroutineScope
import org.kodein.di.DI
import org.kodein.di.bindFactory
import ui.common.home.HomeViewModel
import ui.common.splash.SplashViewModel

val moduleViewModels = DI.Module("ViewModelsModule") {
    /**
     * SPLASH
     */
    bindFactory<CoroutineScope, SplashViewModel> { scope ->
        SplashViewModel(scope)
    }
    /**
     * HOME
     */
    bindFactory<CoroutineScope, HomeViewModel> { scope ->
        HomeViewModel(scope)
    }
}