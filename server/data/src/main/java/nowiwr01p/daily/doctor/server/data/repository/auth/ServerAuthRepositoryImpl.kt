package nowiwr01p.daily.doctor.server.data.repository.auth

import com.nowiwr01p.model.api.request.auth.SignInRequest
import com.nowiwr01p.model.api.request.auth.SignUpRequest
import com.nowiwr01p.model.api.request.verification.SendVerificationCodeRequest
import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import kotlinx.coroutines.withContext
import nowiwr01p.daily.doctor.database.repository.auth.DatabaseAuthRepository
import nowiwr01p.daily.doctor.database.repository.verification.DatabaseVerificationRepository
import nowiwr01p.daily.doctor.server.domain.repository.auth.ServerAuthRepository

class ServerAuthRepositoryImpl(
    private val dispatchers: AppDispatchers,
    private val authRepository: DatabaseAuthRepository,
    private val verificationRepository: DatabaseVerificationRepository
): ServerAuthRepository {

    override suspend fun signIn(request: SignInRequest) = withContext(dispatchers.io) {
        authRepository.signIn(request).also {
            sendVerificationCode(request.email) // TODO: Check if not verified
        }
    }

    override suspend fun signUp(request: SignUpRequest) = withContext(dispatchers.io) {
        authRepository.signUp(request).also {
            sendVerificationCode(request.email)
        }
    }

    private suspend fun sendVerificationCode(email: String) = withContext(dispatchers.io) {
        val request = SendVerificationCodeRequest(email)
        verificationRepository.sendVerificationCode(request)
    }
}