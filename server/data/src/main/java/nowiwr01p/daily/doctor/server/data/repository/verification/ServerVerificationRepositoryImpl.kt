package nowiwr01p.daily.doctor.server.data.repository.verification

import com.nowiwr01p.model.api.request.verification.CheckVerificationCodeRequest
import com.nowiwr01p.model.api.request.verification.SendVerificationCodeRequest
import com.nowiwr01p.model.api.response.token.PinCodeTokenResponse
import com.nowiwr01p.model.api.response.token.TokenResponse
import com.nowiwr01p.model.api.response.verification.SendVerificationCodeResponse
import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import kotlinx.coroutines.withContext
import nowiwr01p.daily.doctor.database.domain.repository.verification.DatabaseVerificationRepository
import nowiwr01p.daily.doctor.server.domain.repository.verification.ServerVerificationRepository

class ServerVerificationRepositoryImpl(
    private val dispatchers: AppDispatchers,
    private val verificationRepository: DatabaseVerificationRepository
) : ServerVerificationRepository {

    override suspend fun sendVerificationCode(
        request: SendVerificationCodeRequest
    ): SendVerificationCodeResponse {
        return withContext(dispatchers.io) {
            verificationRepository.sendVerificationCode(request)
        }
    }

    override suspend fun checkVerificationCode(
        request: CheckVerificationCodeRequest
    ): TokenResponse {
        return withContext(dispatchers.io) {
            verificationRepository.checkVerificationCode(request)
            PinCodeTokenResponse(
                token = "1234" // TODO: Generate token
            )
        }
    }

    override suspend fun deleteExpiredVerificationCodes() = withContext(dispatchers.io) {
        verificationRepository.deleteExpiredVerificationCodes()
    }
}