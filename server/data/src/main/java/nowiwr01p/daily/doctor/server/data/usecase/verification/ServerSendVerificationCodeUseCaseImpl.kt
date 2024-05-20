package nowiwr01p.daily.doctor.server.data.usecase.verification

import com.nowiwr01p.model.api.request.verification.SendVerificationCodeRequest
import com.nowiwr01p.model.api.response.token.TokenResponse
import nowiwr01p.daily.doctor.server.domain.repository.verification.ServerVerificationRepository
import nowiwr01p.daily.doctor.server.domain.usecase.verification.ServerSendVerificationCodeUseCase

class ServerSendVerificationCodeUseCaseImpl(
    private val repository: ServerVerificationRepository
): ServerSendVerificationCodeUseCase {

    override suspend fun execute(input: SendVerificationCodeRequest): TokenResponse {
        return repository.sendVerificationCode(input)
    }
}