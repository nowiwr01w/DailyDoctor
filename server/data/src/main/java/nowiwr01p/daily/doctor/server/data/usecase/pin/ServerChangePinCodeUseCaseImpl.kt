package nowiwr01p.daily.doctor.server.data.usecase.pin

import nowiwr01p.daily.doctor.server.domain.repository.pin.ServerPinCodeRepository
import nowiwr01p.daily.doctor.server.domain.usecase.pin.ServerChangePinCodeUseCase

class ServerChangePinCodeUseCaseImpl(
    private val repository: ServerPinCodeRepository
): ServerChangePinCodeUseCase {

    override suspend fun execute(input: Unit) {
        repository.changePinCode()
    }
}