package nowiwr01p.daily.doctor.database.repository.verification

import com.nowiwr01p.model.api.request.verification.CheckVerificationCodeRequest
import com.nowiwr01p.model.api.request.verification.SendVerificationCodeRequest
import com.nowiwr01p.model.api.response.verification.CheckVerificationCodeResponse
import com.nowiwr01p.model.api.response.verification.SendVerificationCodeResponse
import com.nowiwr01p.model.repository.BaseRepository
import nowiwr01p.daily.doctor.database.table.verification.VerificationCodeEntity
import nowiwr01p.daily.doctor.database.table.verification.VerificationCodeTable
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
            code = "123456"
//            code = "1234567890".toList().shuffled().joinToString().take(4)
        }
        SendVerificationCodeResponse(
            id = insertedVerificationCode.id.toString()
        )
    }

    override suspend fun checkVerificationCode(request: CheckVerificationCodeRequest) = transaction {
        val lastSentCode = VerificationCodeEntity
            .find { VerificationCodeTable.email eq request.email }
            .firstOrNull()
        if (lastSentCode == null) {
            buildError("We somewhat can't process your verification. Please, try again.")
        } else {
            if (request.code == lastSentCode.code) {
                val revokeSessionTimestamp = System.currentTimeMillis() + 10 * 60 * 1000 // TODO: Change to AuthToken
                CheckVerificationCodeResponse(revokeSessionTimestamp = revokeSessionTimestamp)
            } else {
                buildError("Wrong code.")
            }
        }
    }

    private fun deleteUserCodes(email: String) = VerificationCodeEntity
        .find { VerificationCodeTable.email eq email }
        .onEach { it.delete() }
}