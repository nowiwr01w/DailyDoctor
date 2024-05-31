package di

import com.nowiwr01p.model.di.moduleBaseCoroutines
import moduleAppPresentationViewModels
import nowiwr01p.daily.doctor.app_presentation.navigation.di.moduleAppPresentationNavigation
import moduleAppPresentationCoreUi
import moduleAppPresentationTheme

internal fun getAppPresentationModules() = listOf(
    moduleAppPresentationTheme,
    moduleAppPresentationCoreUi,
    moduleAppPresentationNavigation,
    moduleAppPresentationViewModels,
    moduleBaseCoroutines
)