package di

import com.nowiwr01p.local_database.platform.createSettingsModule
import com.nowiwr01p.model.di.moduleBaseCoroutines
import di.theme.moduleTheme
import di.works.moduleAppWorks
import getAppSharedModules
import nowiwr01p.daily.doctor.app_presentation.navigation.di.moduleAppPresentationNavigation

val appModules = listOf(
    moduleTheme,
//    moduleCoreUI,
    moduleAppPresentationNavigation,
//    moduleConfig,
    moduleBaseCoroutines,
    moduleAppWorks,
//    moduleAppViewModels,
    createSettingsModule()
) + getAppSharedModules()