package nowiwr01p.daily.doctor.server.di

import com.nowiwr01p.model.di.moduleBaseCoroutines
import nowiwr01p.daily.doctor.database.di.databaseModules
import nowiwr01p.daily.doctor.server.di.repository.moduleServerRepository
import nowiwr01p.daily.doctor.server.di.routing.moduleServerRouting
import nowiwr01p.daily.doctor.server.di.token.moduleServerToken
import nowiwr01p.daily.doctor.server.di.usecase.moduleServerUseCases
import nowiwr01p.daily.doctor.server.di.work.moduleServerWorks
import nowiwr01p.daily.doctor.tg_sms.di.tgSmsModules

private val allServerModules = listOf(
    moduleServerToken,
    moduleBaseCoroutines,
    moduleServerRepository,
    moduleServerUseCases,
    moduleServerRouting,
    moduleServerWorks
)

val serverModules = allServerModules + databaseModules + tgSmsModules