package nowiwr01p.daily.doctor.database.tables.table.verification

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.UUID

object VerificationCodeTable : UUIDTable("verification_codes") {
    val code = varchar("code", 6)
    val timestamp = long("timestamp")
    val verificationToken = varchar("verificationToken", 64).uniqueIndex()
}

class VerificationCodeEntity(id: EntityID<UUID>) : UUIDEntity(id) {

    var code by VerificationCodeTable.code
    var timestamp by VerificationCodeTable.timestamp
    var verificationToken by VerificationCodeTable.verificationToken

    companion object : UUIDEntityClass<VerificationCodeEntity>(VerificationCodeTable)
}