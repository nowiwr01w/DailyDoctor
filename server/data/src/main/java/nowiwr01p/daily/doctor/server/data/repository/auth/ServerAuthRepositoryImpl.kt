package nowiwr01p.daily.doctor.server.data.repository.auth

import com.nowiwr01p.model.api.request.auth.SignInRequest
import com.nowiwr01p.model.api.request.auth.SignUpRequest
import com.nowiwr01p.model.api.response.token.PinCodeTokenResponse
import com.nowiwr01p.model.api.response.token.TokenResponse
import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import com.nowiwr01p.model.usecase.execute
import kotlinx.coroutines.withContext
import nowiwr01p.daily.doctor.database.domain.repository.auth.DatabaseAuthRepository
import nowiwr01p.daily.doctor.database.domain.repository.pin.DatabasePinCodeRepository
import nowiwr01p.daily.doctor.database.domain.repository.verification.DatabaseVerificationRepository
import nowiwr01p.daily.doctor.server.domain.repository.auth.ServerAuthRepository
import nowiwr01p.daily.doctor.server.token.common.usecase.ServerGenerateCommonTokenUseCase

class ServerAuthRepositoryImpl(
    private val dispatchers: AppDispatchers,
    private val authRepository: DatabaseAuthRepository,
    private val pinCodeRepository: DatabasePinCodeRepository,
    private val verificationRepository: DatabaseVerificationRepository,
    private val generateCommonTokenUseCase: ServerGenerateCommonTokenUseCase
): ServerAuthRepository {

    override suspend fun signIn(request: SignInRequest) = withContext(dispatchers.io) {
        val user = authRepository.signIn(request)
        when {
            user.isEmailVerified -> PinCodeTokenResponse(
                token = user.pinCodeToken,
                isPinCodeSet = pinCodeRepository.isPinCodeSet(user.pinCodeToken)
            )
            else -> sendVerificationCode(request.email)
        }
    }

    override suspend fun signUp(request: SignUpRequest) = withContext(dispatchers.io) {
        authRepository.signUp(request)
        sendVerificationCode(request.email)
    }

    private suspend fun sendVerificationCode(email: String): TokenResponse {
        val token = generateCommonTokenUseCase.execute()
        return verificationRepository.sendVerificationCode(token) // TODO: Send also via Email API
    }
}