package di

import com.nowiwr01p.local_database.platform.getLocalDatabaseModule
import com.nowiwr01p.model.di.moduleBaseCoroutines
import di.theme.moduleTheme
import di.works.moduleAppWorks
import getAppSharedModules
import nowiwr01p.daily.doctor.app_presentation.navigation.di.moduleAppPresentationNavigation
import ui.core_ui.di.moduleAppPresentationCoreUi

private val appPresentationModules = listOf(
    moduleTheme,
    moduleAppPresentationCoreUi,
    moduleAppPresentationNavigation,
    moduleBaseCoroutines,
    moduleAppWorks,
//    moduleAppViewModels,
    getLocalDatabaseModule()
)

private val appSharedModules = getAppSharedModules()

private val localDatabaseModules = getLocalDatabaseModule()

val appModules = localDatabaseModules + appSharedModules + appPresentationModules