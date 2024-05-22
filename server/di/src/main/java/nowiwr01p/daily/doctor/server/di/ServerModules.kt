package nowiwr01p.daily.doctor.server.di

import com.nowiwr01p.model.di.moduleBaseCoroutines
import nowiwr01p.daily.doctor.database.di.databaseModules
import nowiwr01p.daily.doctor.server.di.repository.moduleServerRepository
import nowiwr01p.daily.doctor.server.di.routing.moduleServerRouting
import nowiwr01p.daily.doctor.server.di.token.moduleServerToken
import nowiwr01p.daily.doctor.server.di.usecase.moduleServerUseCases
import nowiwr01p.daily.doctor.server.di.work.moduleServerWorks
import org.kodein.di.DI

private val allServerModules = databaseModules

val koinModules = listOf(
    moduleServerToken,
    moduleBaseCoroutines,
    moduleServerRepository,
    moduleServerUseCases,
    moduleServerRouting,
    moduleServerWorks
)

val serverModules = DI {
    importAll(
        modules = allServerModules,
        allowOverride = true
    )
}