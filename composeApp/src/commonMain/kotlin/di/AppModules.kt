package di

import config.di.moduleConfig
import data.di.moduleData
import navigation.di.moduleNavigation
import org.kodein.di.DI

private val allModules = listOf(
    moduleConfig,
    moduleTheme,
    moduleData,
    moduleViewModels,
    moduleNavigation,
)

val appModules = DI {
    importAll(
        modules = allModules,
        allowOverride = true
    )
}