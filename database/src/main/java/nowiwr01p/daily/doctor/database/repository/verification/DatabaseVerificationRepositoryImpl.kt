package nowiwr01p.daily.doctor.database.repository.verification

import com.nowiwr01p.model.api.request.verification.CheckVerificationCodeRequest
import com.nowiwr01p.model.api.request.verification.SendVerificationCodeRequest
import com.nowiwr01p.model.api.response.verification.SendVerificationCodeResponse
import com.nowiwr01p.model.repository.BaseRepository
import nowiwr01p.daily.doctor.database.table.user.UserEntity
import nowiwr01p.daily.doctor.database.table.user.UserTable
import nowiwr01p.daily.doctor.database.table.verification.VerificationCodeEntity
import nowiwr01p.daily.doctor.database.table.verification.VerificationCodeTable
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseVerificationRepositoryImpl: BaseRepository(), DatabaseVerificationRepository {

    override suspend fun sendVerificationCode(request: SendVerificationCodeRequest) = transaction {
        val lastAskedCode = VerificationCodeEntity
            .find { VerificationCodeTable.email eq request.email }
            .firstOrNull()
        if (lastAskedCode != null && System.currentTimeMillis() <= lastAskedCode.timestamp + 60_000) {
            buildError("Verification code can't be requested.")
        }
        if (lastAskedCode != null) {
            deleteUserCodes(request.email)
        }
        val insertedVerificationCode = VerificationCodeEntity.new {
            email = request.email
            timestamp = request.timestamp
            code = "1234567890".toList().shuffled().joinToString(separator = "").take(6) // TODO: Move to a separated class
        }
        SendVerificationCodeResponse(
            id = insertedVerificationCode.id.toString() // TODO: Change to VerificationToken
        )
    }

    override suspend fun checkVerificationCode(request: CheckVerificationCodeRequest) {
        transaction {
            val lastSentCode = VerificationCodeEntity
                .find { VerificationCodeTable.email eq request.email }
                .firstOrNull()
            if (lastSentCode == null) {
                buildError("We somewhat can't process your verification. Please, try again.")
            } else {
                if (request.code == lastSentCode.code) {
                    setUserVerified(request.email)
                    deleteUserCodes(request.email)
                } else {
                    buildError("Wrong code.")
                }
            }
        }
    }

    private fun deleteUserCodes(email: String) = VerificationCodeEntity
        .find { VerificationCodeTable.email eq email }
        .onEach { it.delete() }

    private fun setUserVerified(email: String) = UserEntity
        .findSingleByAndUpdate(UserTable.email eq email) { entity ->
            entity.isEmailVerified = true
        }
}