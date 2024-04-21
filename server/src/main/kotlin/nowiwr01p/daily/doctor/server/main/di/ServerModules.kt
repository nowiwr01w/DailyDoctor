package nowiwr01p.daily.doctor.server.main.di

import com.nowiwr01p.model.di.moduleBaseCoroutines
import nowiwr01p.daily.doctor.database.di.moduleDatabase
import nowiwr01p.daily.doctor.server.data.di.moduleServerRepository
import nowiwr01p.daily.doctor.server.data.di.moduleServerToken
import nowiwr01p.daily.doctor.server.data.di.moduleServerUseCases
import org.kodein.di.DI

private val baseServerModules = listOf(
    moduleBaseCoroutines,
    moduleServerRepository,
    moduleServerUseCases,
    moduleServerToken
)

private val routingServerModules = listOf(
    moduleServerUserRouting
)

private val databaseModules = listOf(
    moduleDatabase
)

private val allServerModules = baseServerModules + routingServerModules + databaseModules

val serverModules = DI {
    importAll(
        modules = allServerModules,
        allowOverride = true
    )
}