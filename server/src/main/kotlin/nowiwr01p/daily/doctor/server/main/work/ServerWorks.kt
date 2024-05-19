package nowiwr01p.daily.doctor.server.main.work

import nowiwr01p.daily.doctor.server.main.work.works.ServerDeleteExpiredVerificationCodesWork
import org.kodein.di.DI
import org.kodein.di.instance

internal fun executeServerWork(di: DI) {
    val deleteExpiredVerificationCodesWork by di.instance<ServerDeleteExpiredVerificationCodesWork>()
    deleteExpiredVerificationCodesWork.startWork()
}