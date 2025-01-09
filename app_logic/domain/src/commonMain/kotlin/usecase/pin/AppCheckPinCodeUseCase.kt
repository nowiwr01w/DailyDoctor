package usecase.pin

import com.nowiwr01p.model.api.request.pin.CheckPinCodeRequest
import com.nowiwr01p.model.api.response.token.TokenResponse
import com.nowiwr01p.model.usecase.UseCase

interface AppCheckPinCodeUseCase: UseCase<CheckPinCodeRequest, TokenResponse>