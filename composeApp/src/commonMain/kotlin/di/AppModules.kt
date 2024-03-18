package di

import config.di.moduleConfig
import org.kodein.di.DI

private val allModules = listOf(
    moduleConfig
)

val appModules = DI {
    importAll(
        modules = allModules,
        allowOverride = true
    )
}