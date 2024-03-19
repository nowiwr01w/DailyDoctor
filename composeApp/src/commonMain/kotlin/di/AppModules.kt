package di

import config.di.moduleConfig
import data.di.moduleData
import org.kodein.di.DI

private val allModules = listOf(
    moduleConfig,
    moduleTheme,
    moduleData,
    moduleViewModels
)

val appModules = DI {
    importAll(
        modules = allModules,
        allowOverride = true
    )
}