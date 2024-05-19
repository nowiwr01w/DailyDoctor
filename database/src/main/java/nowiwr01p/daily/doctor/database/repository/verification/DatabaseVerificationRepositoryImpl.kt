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
        SendVerificationCodeResponse(
            id = verificationStorage.createVerificationCode(request).id
        )
    }

    override suspend fun checkVerificationCode(request: CheckVerificationCodeRequest) {
        transaction {
            val lastSentCode = verificationStorage.getVerificationCode(request.verificationToken)
            when {
                lastSentCode == null -> buildError(
                    message = "We somewhat can't process your verification. Please, try login again."
                )
                else -> if (request.code != lastSentCode.code) {
                    buildError("Wrong code.")
                } else {
                    userStorage.setVerificationStatus(request.email)
                }
            }
        }
    }

    override suspend fun deleteExpiredVerificationCodes() = transaction {
        verificationStorage.deleteExpiredVerificationCodes()
    }
}