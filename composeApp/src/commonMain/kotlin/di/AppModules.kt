package di

import org.kodein.di.DI

private val moduleConfig = DI.Module("ConfigModule") {
    
}

private val allModules = listOf(
    moduleConfig
)

val appModules = DI {
    importAll(
        modules = allModules,
        allowOverride = true
    )
}