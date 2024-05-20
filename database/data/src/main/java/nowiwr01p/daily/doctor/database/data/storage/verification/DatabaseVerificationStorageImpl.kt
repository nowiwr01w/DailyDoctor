package nowiwr01p.daily.doctor.database.data.storage.verification

import com.nowiwr01p.model.time.TimeInSeconds
import nowiwr01p.daily.doctor.database.domain.storage.verification.DatabaseVerificationStorage
import nowiwr01p.daily.doctor.database.tables.table.verification.VerificationCodeEntity
import nowiwr01p.daily.doctor.database.tables.table.verification.VerificationCodeTable
import org.jetbrains.exposed.sql.SqlExpressionBuilder.lessEq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseVerificationStorageImpl: DatabaseVerificationStorage {

    override fun getVerificationCode(verificationToken: String) = transaction {
        VerificationCodeEntity.find { VerificationCodeTable.verificationToken eq verificationToken }
            .firstOrNull()
            ?.toVerificationCode()
    }

    override fun createVerificationCode(token: String) = transaction {
        VerificationCodeEntity.new {
            timestamp = System.currentTimeMillis()
            verificationToken = token
            code = "1234567890".toList().shuffled() // TODO: Move logic to separated class
                .joinToString(separator = "")
                .take(6)
        }.toVerificationCode()
    }

    override fun deleteExpiredVerificationCodes() { // TODO: Create micro service
        transaction {
            val expiredTime = run {
                System.currentTimeMillis() - TimeInSeconds.PeriodMinutes(15).toMillis()
            }
            VerificationCodeTable.deleteWhere { timestamp lessEq expiredTime }
        }
    }
}