package nowiwr01p.daily.doctor.server.data.usecase.pin

import com.nowiwr01p.model.api.request.pin.CreatePinCodeRequest
import com.nowiwr01p.model.api.response.token.TokenResponse
import nowiwr01p.daily.doctor.server.domain.repository.pin.ServerPinCodeRepository
import nowiwr01p.daily.doctor.server.domain.usecase.pin.ServerCreatePinCodeUseCase

class ServerCreatePinCodeUseCaseImpl(
    private val repository: ServerPinCodeRepository
): ServerCreatePinCodeUseCase {

    override suspend fun execute(input: CreatePinCodeRequest): TokenResponse {
        return repository.createPinCode(input)
    }
}