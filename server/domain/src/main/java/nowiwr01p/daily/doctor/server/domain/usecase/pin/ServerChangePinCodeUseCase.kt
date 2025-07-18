package nowiwr01p.daily.doctor.server.domain.usecase.pin

import com.nowiwr01p.model.api.request.pin.ChangePinCodeRequest
import com.nowiwr01p.model.api.response.token.TokenResponse
import com.nowiwr01p.model.usecase.UseCase

interface ServerChangePinCodeUseCase: UseCase<ChangePinCodeRequest, TokenResponse>