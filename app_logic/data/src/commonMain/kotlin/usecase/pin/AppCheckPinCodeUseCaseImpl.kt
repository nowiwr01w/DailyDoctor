package usecase.pin

import com.nowiwr01p.model.api.request.pin.CheckPinCodeRequest
import com.nowiwr01p.model.api.response.token.TokenResponse
import repository.pin.AppPinRepository

class AppCheckPinCodeUseCaseImpl(
    private val repository: AppPinRepository
): AppCheckPinCodeUseCase {

    override suspend fun execute(input: CheckPinCodeRequest): TokenResponse {
        return repository.checkPinCode(input)
    }
}