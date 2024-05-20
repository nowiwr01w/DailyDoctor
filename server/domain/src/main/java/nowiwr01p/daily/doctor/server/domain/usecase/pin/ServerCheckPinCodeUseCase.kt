package nowiwr01p.daily.doctor.server.domain.usecase.pin

import com.nowiwr01p.model.api.request.pin.CheckPinCodeRequest
import com.nowiwr01p.model.api.response.token.TokenResponse
import com.nowiwr01p.model.usecase.UseCase

interface ServerCheckPinCodeUseCase: UseCase<CheckPinCodeRequest, TokenResponse>