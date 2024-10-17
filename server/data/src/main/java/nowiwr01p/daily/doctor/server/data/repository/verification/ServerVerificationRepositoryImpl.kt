package nowiwr01p.daily.doctor.server.data.repository.verification

import com.nowiwr01p.model.api.request.verification.CheckVerificationCodeRequest
import com.nowiwr01p.model.api.request.verification.SendVerificationCodeRequest
import com.nowiwr01p.model.repository.BaseRepository
import com.nowiwr01p.model.usecase.execute
import nowiwr01p.daily.doctor.database.domain.generator.VerificationCodeGenerator
import nowiwr01p.daily.doctor.database.domain.repository.verification.DatabaseVerificationRepository
import nowiwr01p.daily.doctor.server.domain.repository.verification.ServerVerificationRepository
import nowiwr01p.daily.doctor.server.token.common.usecase.ServerGenerateCommonTokenUseCase
import nowiwr01p.daily.doctor.tg_sms.domain.api.requests.VerificationRequest
import nowiwr01p.daily.doctor.tg_sms.domain.usecase.TgSendVerificationCodeUseCase

class ServerVerificationRepositoryImpl(
    private val databaseVerificationRepository: DatabaseVerificationRepository,
    private val verificationCodeGenerator: VerificationCodeGenerator,
    private val tgSendVerificationCodeUseCase: TgSendVerificationCodeUseCase,
    private val generateCommonTokenUseCase: ServerGenerateCommonTokenUseCase
) : BaseRepository(), ServerVerificationRepository {

    override suspend fun sendVerificationCode(request: SendVerificationCodeRequest) = io {
        val verificationCode = verificationCodeGenerator.generateVerificationCode()
        val response = tgSendVerificationCodeUseCase.execute(
            input = VerificationRequest(
                phoneNumber = request.phone,
                code = verificationCode,
                expiredAfterSeconds = 60
            )
        )
        if (response.isSuccess) {
            val verificationCodeToken = generateCommonTokenUseCase.execute()
            databaseVerificationRepository.sendVerificationCode(
                code = verificationCode,
                token = verificationCodeToken
            )
        } else {
            buildError("We can't send verification code to this number.")
        }
    }

    override suspend fun checkVerificationCode(request: CheckVerificationCodeRequest) = io {
        val pinCodeResponseToken = generateCommonTokenUseCase.execute()
        databaseVerificationRepository.checkVerificationCode(pinCodeResponseToken, request)
    }

    override suspend fun deleteExpiredVerificationCodes() = io {
        databaseVerificationRepository.deleteExpiredVerificationCodes()
    }
}