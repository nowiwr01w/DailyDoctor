package nowiwr01p.daily.doctor.server.domain.usecase.verification

import com.nowiwr01p.model.api.request.verification.VerificationCodeRequest
import com.nowiwr01p.model.api.response.verification.VerificationCodeResponse
import com.nowiwr01p.model.usecase.UseCase

interface ServerSendVerificationCodeUseCase: UseCase<VerificationCodeRequest, VerificationCodeResponse>