package di

import com.nowiwr01p.model.di.moduleBaseCoroutines
import moduleAppPresentationViewModels
import moduleAppPresentationCoreUi
import moduleAppPresentationTheme
import nowiwr01p.daily.doctor.app_ui.navigation.di.getAppPresentationNavigationModule

internal fun getAppPresentationModules() = listOf(
    moduleAppPresentationTheme,
    moduleAppPresentationCoreUi,
    getAppPresentationNavigationModule(),
    moduleAppPresentationViewModels,
    moduleBaseCoroutines
)
