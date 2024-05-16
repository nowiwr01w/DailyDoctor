package nowiwr01p.daily.doctor.server.data.usecase.verification

import com.nowiwr01p.model.api.request.verification.CheckVerificationCodeRequest
import com.nowiwr01p.model.api.response.verification.CheckVerificationCodeResponse
import nowiwr01p.daily.doctor.server.domain.repository.verification.ServerVerificationRepository
import nowiwr01p.daily.doctor.server.domain.usecase.verification.ServerCheckVerificationCodeUseCase

class ServerCheckVerificationCodeUseCaseImpl(
    private val repository: ServerVerificationRepository
): ServerCheckVerificationCodeUseCase {

    override suspend fun execute(input: CheckVerificationCodeRequest): CheckVerificationCodeResponse {
        return repository.checkVerificationCode(input)
    }
}