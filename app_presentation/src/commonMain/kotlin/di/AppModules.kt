package di

import com.nowiwr01p.local_database.platform.createSettingsModule
import com.nowiwr01p.model.di.moduleBaseCoroutines
import config.di.moduleConfig
import moduleAppSharedData
import moduleAppSharedDomain
import moduleAppAuthDataValidators
import navigation.di.moduleNavigation
import ui.core_ui.di.moduleCoreUI

val appModules = listOf(
    moduleTheme,
    moduleCoreUI,
    moduleNavigation,
    moduleConfig,
    moduleBaseCoroutines,
    moduleAppWorks,
    moduleAppViewModels,
    moduleAppSharedDomain,
    moduleAppSharedData,
    moduleAppAuthDataValidators,
    createSettingsModule()
)