package nowiwr01p.daily.doctor.server.data.usecase.pin

import com.nowiwr01p.model.api.request.pin.CheckPinCodeRequest
import com.nowiwr01p.model.api.response.token.TokenResponse
import nowiwr01p.daily.doctor.server.domain.repository.pin.ServerPinCodeRepository
import nowiwr01p.daily.doctor.server.domain.usecase.pin.ServerCheckPinCodeUseCase

class ServerCheckPinCodeUseCaseImpl(
    private val repository: ServerPinCodeRepository
): ServerCheckPinCodeUseCase {

    override suspend fun execute(input: CheckPinCodeRequest): TokenResponse {
        return repository.checkPinCode(input)
    }
}