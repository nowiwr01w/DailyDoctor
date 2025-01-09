package usecase.pin

import com.nowiwr01p.model.api.request.pin.CreatePinCodeRequest
import com.nowiwr01p.model.api.response.token.TokenResponse
import repository.pin.AppPinRepository

class AppCreatePinCodeUseCaseImpl(
    private val repository: AppPinRepository
): AppCreatePinCodeUseCase {

    override suspend fun execute(input: CreatePinCodeRequest): TokenResponse {
        return repository.createPinCode(input)
    }
}