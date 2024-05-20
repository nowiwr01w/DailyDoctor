package nowiwr01p.daily.doctor.server.data.usecase.pin

import nowiwr01p.daily.doctor.server.domain.repository.pin.ServerPinCodeRepository
import nowiwr01p.daily.doctor.server.domain.usecase.pin.ServerCheckPinCodeUseCase

class ServerCheckPinCodeUseCaseImpl(
    private val repository: ServerPinCodeRepository
): ServerCheckPinCodeUseCase {

    override suspend fun execute(input: Unit) {
        repository.checkPinCode()
    }
}