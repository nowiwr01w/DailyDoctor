package di

import config.di.moduleConfig
import data.di.moduleData
import data.di.moduleUserDataValidators
import domain.di.moduleDomain
import navigation.di.moduleNavigation
import org.kodein.di.DI
import ui.core_ui.di.moduleCoreUI

private val allModules = listOf(
    moduleConfig,
    moduleTheme,
    moduleUserDataValidators,
    moduleDomain,
    moduleData,
    moduleViewModels,
    moduleNavigation,
    moduleCoreUI
)

val appModules = DI {
    importAll(
        modules = allModules,
        allowOverride = true
    )
}