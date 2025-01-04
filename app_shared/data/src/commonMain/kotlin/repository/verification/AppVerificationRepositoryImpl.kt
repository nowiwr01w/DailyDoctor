package repository.verification

import api.verification.VerificationApi
import com.nowiwr01p.model.api.request.verification.CheckVerificationCodeRequest
import com.nowiwr01p.model.api.request.verification.SendVerificationCodeRequest
import repository.AppBaseRepository

class AppVerificationRepositoryImpl(
    private val api: VerificationApi
): AppBaseRepository(), AppVerificationRepository {

    override suspend fun sendVerificationCode(request: SendVerificationCodeRequest) = io {
        api.sendVerificationCode(request)
    }

    override suspend fun checkVerificationCode(request: CheckVerificationCodeRequest) = io {
        api.checkVerificationCode(request)
    }
}