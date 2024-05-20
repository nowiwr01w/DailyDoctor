package nowiwr01p.daily.doctor.server.data.usecase.pin

import nowiwr01p.daily.doctor.server.domain.repository.pin.ServerPinCodeRepository
import nowiwr01p.daily.doctor.server.domain.usecase.pin.ServerDeletePinCodeUseCase

class ServerDeletePinCodeUseCaseImpl(
    private val repository: ServerPinCodeRepository
): ServerDeletePinCodeUseCase {

    override suspend fun execute(input: Unit) {
        repository.deletePinCode()
    }
}