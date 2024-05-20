package nowiwr01p.daily.doctor.server.data.usecase.pin

import nowiwr01p.daily.doctor.server.domain.repository.pin.ServerPinCodeRepository
import nowiwr01p.daily.doctor.server.domain.usecase.pin.ServerCreatePinCodeUseCase

class ServerCreatePinCodeUseCaseImpl(
    private val repository: ServerPinCodeRepository
): ServerCreatePinCodeUseCase {

    override suspend fun execute(input: Unit) {
        repository.createPinCode()
    }
}