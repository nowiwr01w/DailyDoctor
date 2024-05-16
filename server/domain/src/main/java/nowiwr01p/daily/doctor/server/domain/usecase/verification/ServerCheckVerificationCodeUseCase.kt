package nowiwr01p.daily.doctor.server.domain.usecase.verification

import com.nowiwr01p.model.api.request.verification.CheckVerificationCodeRequest
import com.nowiwr01p.model.api.response.verification.AuthTokenResponse
import com.nowiwr01p.model.usecase.UseCase

interface ServerCheckVerificationCodeUseCase: UseCase<CheckVerificationCodeRequest, AuthTokenResponse>