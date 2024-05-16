package repository.verification

import api.verification.VerificationApi
import com.nowiwr01p.model.api.request.verification.CheckVerificationCodeRequest
import com.nowiwr01p.model.api.response.verification.CheckVerificationCodeResponse
import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import com.nowiwr01p.model.repository.BaseRepository
import kotlinx.coroutines.withContext

class AppVerificationRepositoryImpl(
    private val api: VerificationApi,
    private val dispatchers: AppDispatchers
): BaseRepository(), AppVerificationRepository {

    override suspend fun checkVerificationCode(
        request: CheckVerificationCodeRequest
    ): CheckVerificationCodeResponse {
        return withContext(dispatchers.io) {
            api.checkVerificationCode(request)
        }
    }
}