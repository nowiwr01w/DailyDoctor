package di

import com.nowiwr01p.local_database.platform.createSettingsModule
import com.nowiwr01p.model.di.moduleBaseCoroutines
import config.di.moduleConfig
import data.di.moduleDataApp
import data.di.moduleUserDataValidators
import domain.di.moduleDomainApp
import navigation.di.moduleNavigation
import ui.core_ui.di.moduleCoreUI

val appModules = listOf(
    moduleTheme,
    moduleCoreUI,
    moduleNavigation,
    moduleConfig,
    moduleBaseCoroutines,
    moduleAppWorks,
    moduleViewModels,
    moduleDomainApp,
    moduleDomainShared,
    moduleDataApp,
    moduleDataShared,
    moduleUserDataValidators,
    createSettingsModule()
)