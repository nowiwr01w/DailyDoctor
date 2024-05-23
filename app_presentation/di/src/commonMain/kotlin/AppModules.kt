package di

import com.nowiwr01p.local_database.platform.createSettingsModule
import com.nowiwr01p.model.di.moduleBaseCoroutines
import di.theme.moduleTheme
//import di.view_models.moduleAppViewModels
import di.works.moduleAppWorks
import getAppSharedModules

val appModules = listOf(
    moduleTheme,
//    moduleCoreUI,
//    moduleNavigation,
//    moduleConfig,
    moduleBaseCoroutines,
    moduleAppWorks,
//    moduleAppViewModels,
    createSettingsModule()
) + getAppSharedModules()