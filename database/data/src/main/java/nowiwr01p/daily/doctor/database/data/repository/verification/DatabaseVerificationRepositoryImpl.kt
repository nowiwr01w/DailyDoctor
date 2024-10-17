package nowiwr01p.daily.doctor.database.data.repository.verification

import com.nowiwr01p.model.api.request.verification.CheckVerificationCodeRequest
import com.nowiwr01p.model.api.response.token.PinCodeTokenResponse
import com.nowiwr01p.model.api.response.token.TokenResponse
import com.nowiwr01p.model.api.response.token.VerificationTokenResponse
import com.nowiwr01p.model.repository.BaseRepository
import nowiwr01p.daily.doctor.database.domain.repository.verification.DatabaseVerificationRepository
import nowiwr01p.daily.doctor.database.domain.storage.user.DatabaseUserStorage
import nowiwr01p.daily.doctor.database.domain.storage.verification.DatabaseVerificationStorage
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseVerificationRepositoryImpl(
    private val userStorage: DatabaseUserStorage,
    private val verificationStorage: DatabaseVerificationStorage
): BaseRepository(), DatabaseVerificationRepository {

    override suspend fun sendVerificationCode(token: String, code: String) = transaction {
        verificationStorage.createVerificationCode(token, code)
        VerificationTokenResponse(token)
    }

    override suspend fun checkVerificationCode(
        token: String,
        request: CheckVerificationCodeRequest
    ): TokenResponse {
        return transaction {
            val lastSentCode = verificationStorage.getVerificationCode(request.verificationToken)
            when {
                lastSentCode == null -> buildError(
                    message = "We somewhat can't process your verification. Please, try login again."
                )
                else -> if (request.code != lastSentCode) {
                    buildError("Wrong code.")
                } else {
                    userStorage.setUserPinCodeToken(request.phone, token)
                    userStorage.setUserVerified(request.phone)
                    PinCodeTokenResponse(
                        token = token,
                        isPinCodeSet = false
                    )
                }
            }
        }
    }

    override suspend fun deleteExpiredVerificationCodes() = transaction {
        verificationStorage.deleteExpiredVerificationCodes()
    }
}