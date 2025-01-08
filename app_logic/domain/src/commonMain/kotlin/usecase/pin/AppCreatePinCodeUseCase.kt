package usecase.pin

import com.nowiwr01p.model.api.request.pin.CreatePinCodeRequest
import com.nowiwr01p.model.api.response.token.TokenResponse
import com.nowiwr01p.model.usecase.UseCase

interface AppCreatePinCodeUseCase: UseCase<CreatePinCodeRequest, TokenResponse>