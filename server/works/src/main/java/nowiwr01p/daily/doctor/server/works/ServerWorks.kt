package nowiwr01p.daily.doctor.server.works

import nowiwr01p.daily.doctor.server.works.verification.ServerDeleteExpiredVerificationCodesWork
import org.kodein.di.DI
import org.kodein.di.instance

fun scheduleServerWorks(di: DI) {
    val deleteExpiredVerificationCodesWork by di.instance<ServerDeleteExpiredVerificationCodesWork>()
    deleteExpiredVerificationCodesWork.startWork()
}