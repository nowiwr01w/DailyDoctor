package nowiwr01p.daily.doctor.database.data.storage.verification

import com.nowiwr01p.model.time.TimeInSeconds
import nowiwr01p.daily.doctor.database.domain.generator.VerificationCodeGenerator
import nowiwr01p.daily.doctor.database.domain.storage.verification.DatabaseVerificationStorage
import nowiwr01p.daily.doctor.database.tables.table.verification.VerificationCodeEntity
import nowiwr01p.daily.doctor.database.tables.table.verification.VerificationCodeTable
import org.jetbrains.exposed.sql.SqlExpressionBuilder.lessEq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseVerificationStorageImpl(
    private val generator: VerificationCodeGenerator
): DatabaseVerificationStorage {

    override fun getVerificationCode(verificationToken: String) = transaction {
        VerificationCodeEntity.find { VerificationCodeTable.verificationToken eq verificationToken }
            .firstOrNull()
            ?.code
    }

    override fun createVerificationCode(token: String) = transaction {
        val verificationCodeEntity = VerificationCodeEntity.new {
            timestamp = System.currentTimeMillis()
            verificationToken = token
            code = generator.generateVerificationCode()
        }
        verificationCodeEntity.code
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