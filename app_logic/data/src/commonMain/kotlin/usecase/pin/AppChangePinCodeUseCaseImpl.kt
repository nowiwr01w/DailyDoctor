package usecase.pin

import com.nowiwr01p.model.api.request.pin.ChangePinCodeRequest
import com.nowiwr01p.model.api.response.token.TokenResponse
import repository.pin.AppPinRepository

class AppChangePinCodeUseCaseImpl(
    private val repository: AppPinRepository
): AppChangePinCodeUseCase {

    override suspend fun execute(input: ChangePinCodeRequest): TokenResponse {
        return repository.changePinCode(input)
    }
}