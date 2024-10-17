package nowiwr01p.daily.doctor.server.data.repository.auth

import com.nowiwr01p.model.api.request.auth.SignInRequest
import com.nowiwr01p.model.api.request.auth.SignUpRequest
import com.nowiwr01p.model.api.request.verification.SendVerificationCodeRequest
import com.nowiwr01p.model.api.response.token.PinCodeTokenResponse
import com.nowiwr01p.model.api.response.token.TokenResponse
import com.nowiwr01p.model.repository.BaseRepository
import nowiwr01p.daily.doctor.database.domain.repository.auth.DatabaseAuthRepository
import nowiwr01p.daily.doctor.database.domain.repository.pin.DatabasePinCodeRepository
import nowiwr01p.daily.doctor.server.domain.repository.auth.ServerAuthRepository
import nowiwr01p.daily.doctor.server.domain.repository.verification.ServerVerificationRepository

class ServerAuthRepositoryImpl(
    private val authRepository: DatabaseAuthRepository,
    private val pinCodeRepository: DatabasePinCodeRepository,
    private val verificationRepository: ServerVerificationRepository,
): BaseRepository(), ServerAuthRepository {

    override suspend fun signIn(request: SignInRequest) = io {
        val user = authRepository.signIn(request)
        when {
            user.isPhoneVerified -> PinCodeTokenResponse(
                token = user.pinCodeToken,
                isPinCodeSet = pinCodeRepository.isPinCodeSet(user.pinCodeToken)
            )
            else -> sendVerificationCode(request.phone)
        }
    }

    override suspend fun signUp(request: SignUpRequest) = io {
        authRepository.signUp(request)
        sendVerificationCode(request.phone)
    }

    private suspend fun sendVerificationCode(phone: String): TokenResponse {
        val sendVerificationCodeRequest = SendVerificationCodeRequest(phone)
        return verificationRepository.sendVerificationCode(sendVerificationCodeRequest)
    }
}