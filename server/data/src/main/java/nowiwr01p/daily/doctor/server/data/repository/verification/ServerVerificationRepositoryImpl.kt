package nowiwr01p.daily.doctor.server.data.repository.verification

import com.nowiwr01p.model.api.request.verification.VerificationCodeRequest
import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import nowiwr01p.daily.doctor.database.repository.verification.DatabaseVerificationRepository
import nowiwr01p.daily.doctor.server.domain.repository.verification.ServerVerificationRepository

class ServerVerificationRepositoryImpl(
    private val dispatchers: AppDispatchers,
    private val repository: DatabaseVerificationRepository
): ServerVerificationRepository {

    override suspend fun sendVerificationCode(request: VerificationCodeRequest) = TODO()
}