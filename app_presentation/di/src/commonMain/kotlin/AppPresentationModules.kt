package di

import com.nowiwr01p.model.di.moduleBaseCoroutines
import moduleAppPresentationTheme
import moduleAppPresentationViewModels
import nowiwr01p.daily.doctor.app_presentation.navigation.di.moduleAppPresentationNavigation
import ui.core_ui.di.moduleAppPresentationCoreUi

internal fun getAppPresentationModules() = listOf(
    moduleAppPresentationTheme,
    moduleAppPresentationCoreUi,
    moduleAppPresentationNavigation,
    moduleAppPresentationViewModels,
    moduleBaseCoroutines
)