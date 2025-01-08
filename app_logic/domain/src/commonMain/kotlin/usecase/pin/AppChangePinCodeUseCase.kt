package usecase.pin

import com.nowiwr01p.model.api.request.pin.ChangePinCodeRequest
import com.nowiwr01p.model.api.response.token.TokenResponse
import com.nowiwr01p.model.usecase.UseCase

interface AppChangePinCodeUseCase: UseCase<ChangePinCodeRequest, TokenResponse>