package nowiwr01p.daily.doctor.database.repository.verification

import com.nowiwr01p.model.api.request.verification.CheckVerificationCodeRequest
import com.nowiwr01p.model.api.request.verification.SendVerificationCodeRequest
import com.nowiwr01p.model.api.response.verification.SendVerificationCodeResponse
import com.nowiwr01p.model.repository.BaseRepository
import nowiwr01p.daily.doctor.database.storage.user.DatabaseUserStorage
import nowiwr01p.daily.doctor.database.storage.verification.DatabaseVerificationStorage
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseVerificationRepositoryImpl(
    private val userStorage: DatabaseUserStorage,
    private val verificationStorage: DatabaseVerificationStorage
): BaseRepository(), DatabaseVerificationRepository {

    override suspend fun sendVerificationCode(request: SendVerificationCodeRequest) = transaction {
        verificationStorage.getVerificationCode(request.email).let { lastAskedCode ->
            if (lastAskedCode != null) {
                verificationStorage.deleteVerificationCodes(request.email)
            }
        }
        SendVerificationCodeResponse(
            id = verificationStorage.createVerificationCode(request).id
        )
    }

    override suspend fun checkVerificationCode(request: CheckVerificationCodeRequest) {
        transaction {
            val lastSentCode = verificationStorage.getVerificationCode(request.email)
            when {
                lastSentCode == null -> buildError(
                    message = "We somewhat can't process your verification. Please, try again."
                )
                else -> if (request.code != lastSentCode.code) {
                    buildError("Wrong code.")
                } else {
                    userStorage.setVerificationStatus(request.email)
                    verificationStorage.deleteVerificationCodes(request.email)
                }
            }
        }
    }
}