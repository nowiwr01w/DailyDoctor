package nowiwr01p.daily.doctor.database.storage.verification

import com.nowiwr01p.model.api.request.verification.SendVerificationCodeRequest
import nowiwr01p.daily.doctor.database.table.verification.VerificationCodeEntity
import nowiwr01p.daily.doctor.database.table.verification.VerificationCodeTable
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseVerificationStorageImpl: DatabaseVerificationStorage {

    override fun getVerificationCode(verificationToken: String) = transaction {
        VerificationCodeEntity.find { VerificationCodeTable.verificationToken eq verificationToken }
            .firstOrNull()
            ?.toVerificationCode()
    }

    override fun createVerificationCode(request: SendVerificationCodeRequest) = transaction {
        VerificationCodeEntity.new {
            verificationToken = "1234" // TODO: Generate Token
            code = "1234567890".toList().shuffled() // TODO: Move logic to separated class
                .joinToString(separator = "")
                .take(6)
        }.toVerificationCode()
    }

    override fun deleteVerificationCodes(verificationToken: String): Unit = transaction {
        VerificationCodeEntity
            .find { VerificationCodeTable.verificationToken eq verificationToken }
            .onEach { it.delete() }
    }
}