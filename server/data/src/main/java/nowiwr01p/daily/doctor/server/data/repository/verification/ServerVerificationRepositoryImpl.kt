package nowiwr01p.daily.doctor.server.data.repository.verification

import com.nowiwr01p.model.api.request.verification.CheckVerificationCodeRequest
import com.nowiwr01p.model.api.request.verification.SendVerificationCodeRequest
import com.nowiwr01p.model.api.response.token.TokenResponse
import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import com.nowiwr01p.model.usecase.execute
import kotlinx.coroutines.withContext
import nowiwr01p.daily.doctor.database.domain.repository.verification.DatabaseVerificationRepository
import nowiwr01p.daily.doctor.server.domain.repository.verification.ServerVerificationRepository
import nowiwr01p.daily.doctor.server.token.common.usecase.ServerGenerateCommonTokenUseCase

class ServerVerificationRepositoryImpl(
    private val dispatchers: AppDispatchers,
    private val verificationRepository: DatabaseVerificationRepository,
    private val generateCommonTokenUseCase: ServerGenerateCommonTokenUseCase
) : ServerVerificationRepository {

    override suspend fun sendVerificationCode(request: SendVerificationCodeRequest): TokenResponse {
        return withContext(dispatchers.io) {
            val verificationResponseToken = generateCommonTokenUseCase.execute()
            verificationRepository.sendVerificationCode(verificationResponseToken)
        }
    }

    override suspend fun checkVerificationCode(request: CheckVerificationCodeRequest): TokenResponse {
        return withContext(dispatchers.io) {
            val pinCodeResponseToken = generateCommonTokenUseCase.execute()
            verificationRepository.checkVerificationCode(pinCodeResponseToken, request)
        }
    }

    override suspend fun deleteExpiredVerificationCodes() = withContext(dispatchers.io) {
        verificationRepository.deleteExpiredVerificationCodes()
    }
}