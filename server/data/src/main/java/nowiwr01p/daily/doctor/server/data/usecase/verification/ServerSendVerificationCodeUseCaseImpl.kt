package nowiwr01p.daily.doctor.server.data.usecase.verification

import com.nowiwr01p.model.api.request.verification.SendVerificationCodeRequest
import com.nowiwr01p.model.api.response.verification.SendVerificationCodeResponse
import nowiwr01p.daily.doctor.server.domain.repository.verification.ServerVerificationRepository
import nowiwr01p.daily.doctor.server.domain.usecase.verification.ServerSendVerificationCodeUseCase

class ServerSendVerificationCodeUseCaseImpl(
    private val repository: ServerVerificationRepository
): ServerSendVerificationCodeUseCase {

    override suspend fun execute(input: SendVerificationCodeRequest): SendVerificationCodeResponse {
        return repository.sendVerificationCode(input)
    }
}