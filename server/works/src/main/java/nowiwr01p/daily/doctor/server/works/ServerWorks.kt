package nowiwr01p.daily.doctor.server.works

import io.ktor.server.application.Application
import nowiwr01p.daily.doctor.server.works.verification.ServerDeleteExpiredVerificationCodesWork
import org.koin.ktor.ext.inject

fun Application.scheduleServerWorks() {
    val deleteExpiredVerificationCodesWork by inject<ServerDeleteExpiredVerificationCodesWork>()
    deleteExpiredVerificationCodesWork.startWork()
}