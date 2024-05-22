package di

import com.nowiwr01p.local_database.platform.createSettingsModule
import com.nowiwr01p.model.di.moduleBaseCoroutines
import config.di.moduleConfig
import data.di.moduleData
import data.di.moduleUserDataValidators
import domain.di.moduleDomain
import navigation.di.moduleNavigation
import org.kodein.di.DI
import ui.core_ui.di.moduleCoreUI

private val allModules = listOf(
    moduleConfig,
    moduleBaseCoroutines,
    moduleTheme,
    moduleUserDataValidators,
    moduleDomain,
    moduleData,
    moduleViewModels,
    moduleAppWorks,
    moduleNavigation,
    moduleCoreUI,
    moduleDomainApp,
    moduleAppData
)

val koinModules = listOf(
    createSettingsModule()
)

val appModules = DI {
    importAll(
        modules = allModules,
        allowOverride = true
    )
}