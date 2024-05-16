package nowiwr01p.daily.doctor.server.data.repository.verification

import com.nowiwr01p.model.api.request.verification.CheckVerificationCodeRequest
import com.nowiwr01p.model.api.request.verification.SendVerificationCodeRequest
import com.nowiwr01p.model.api.response.verification.CheckVerificationCodeResponse
import com.nowiwr01p.model.api.response.verification.SendVerificationCodeResponse
import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import kotlinx.coroutines.withContext
import nowiwr01p.daily.doctor.database.repository.verification.DatabaseVerificationRepository
import nowiwr01p.daily.doctor.server.domain.repository.verification.ServerVerificationRepository

class ServerVerificationRepositoryImpl(
    private val dispatchers: AppDispatchers,
    private val repository: DatabaseVerificationRepository
) : ServerVerificationRepository {

    override suspend fun sendVerificationCode(request: SendVerificationCodeRequest): SendVerificationCodeResponse {
        return withContext(dispatchers.io) {
            repository.sendVerificationCode(request)
        }
    }

    override suspend fun checkVerificationCode(request: CheckVerificationCodeRequest): CheckVerificationCodeResponse {
        return withContext(dispatchers.io) {
            repository.checkVerificationCode(request)
        }
    }
}