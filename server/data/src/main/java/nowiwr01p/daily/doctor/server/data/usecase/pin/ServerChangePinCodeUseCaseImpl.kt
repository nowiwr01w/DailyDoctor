package nowiwr01p.daily.doctor.server.data.usecase.pin

import com.nowiwr01p.model.api.request.pin.ChangePinCodeRequest
import com.nowiwr01p.model.api.response.token.TokenResponse
import nowiwr01p.daily.doctor.server.domain.repository.pin.ServerPinCodeRepository
import nowiwr01p.daily.doctor.server.domain.usecase.pin.ServerChangePinCodeUseCase

class ServerChangePinCodeUseCaseImpl(
    private val repository: ServerPinCodeRepository
): ServerChangePinCodeUseCase {

    override suspend fun execute(input: ChangePinCodeRequest): TokenResponse {
        return repository.changePinCode(input)
    }
}