package nowiwr01p.daily.doctor.server.data.usecase.verification

import nowiwr01p.daily.doctor.server.domain.repository.verification.ServerVerificationRepository
import nowiwr01p.daily.doctor.server.domain.usecase.verification.ServerDeleteExpiredVerificationCodesUseCase

class ServerDeleteExpiredVerificationCodesUseCaseImpl(
    private val repository: ServerVerificationRepository
): ServerDeleteExpiredVerificationCodesUseCase {

    override suspend fun execute(input: Unit) = repository.deleteExpiredVerificationCodes()
}