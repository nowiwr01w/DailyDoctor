package nowiwr01p.daily.doctor.database.storage.verification

import com.nowiwr01p.model.api.request.verification.SendVerificationCodeRequest
import nowiwr01p.daily.doctor.database.table.verification.VerificationCodeEntity
import nowiwr01p.daily.doctor.database.table.verification.VerificationCodeTable
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseVerificationStorageImpl: DatabaseVerificationStorage {

    override fun getVerificationCode(email: String) = transaction {
        VerificationCodeEntity.find { VerificationCodeTable.email eq email }
            .firstOrNull()
            ?.toVerificationCode()
    }

    override fun createVerificationCode(request: SendVerificationCodeRequest) = transaction {
        VerificationCodeEntity.new {
            email = request.email
            code = "1234567890".toList().shuffled()
                .joinToString(separator = "")
                .take(6)
        }.toVerificationCode()
    }

    override fun deleteVerificationCodes(email: String): Unit = transaction {
        VerificationCodeEntity
            .find { VerificationCodeTable.email eq email }
            .onEach { it.delete() }
    }
}