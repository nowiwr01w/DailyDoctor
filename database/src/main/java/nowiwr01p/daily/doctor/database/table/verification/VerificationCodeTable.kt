package nowiwr01p.daily.doctor.database.table.verification

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.UUID

object VerificationCodeTable : UUIDTable("verification_codes") {
    val email = varchar("email", 64).uniqueIndex()
    val timestamp = long("timestamp")
    val code = varchar("code", 6)
}

class VerificationCodeEntity(id: EntityID<UUID>) : UUIDEntity(id) {

    var email by VerificationCodeTable.email
    var timestamp by VerificationCodeTable.timestamp
    var code by VerificationCodeTable.code

    companion object : UUIDEntityClass<VerificationCodeEntity>(VerificationCodeTable)
}