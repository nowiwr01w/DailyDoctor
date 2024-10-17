package repository.verification

import api.verification.VerificationApi
import com.nowiwr01p.model.api.request.verification.CheckVerificationCodeRequest
import com.nowiwr01p.model.api.request.verification.SendVerificationCodeRequest
import com.nowiwr01p.model.api.response.token.TokenResponse
import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import com.nowiwr01p.model.repository.BaseRepository
import kotlinx.coroutines.withContext

class AppVerificationRepositoryImpl(
    private val api: VerificationApi
): BaseRepository(), AppVerificationRepository {

    override suspend fun sendVerificationCode(request: SendVerificationCodeRequest) = io {
        api.sendVerificationCode(request)
    }

    override suspend fun checkVerificationCode(request: CheckVerificationCodeRequest) = io {
        api.checkVerificationCode(request)
    }
}