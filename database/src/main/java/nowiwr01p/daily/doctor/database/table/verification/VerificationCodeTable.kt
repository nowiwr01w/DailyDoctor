package nowiwr01p.daily.doctor.database.table.verification

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.UUID

object VerificationCodeTable : UUIDTable("verification_codes") {
    val code = varchar("code", 6)
    val verificationToken = varchar("verificationToken", 64).uniqueIndex()
}

class VerificationCodeEntity(id: EntityID<UUID>) : UUIDEntity(id) {

    var code by VerificationCodeTable.code
    var verificationToken by VerificationCodeTable.verificationToken

    fun toVerificationCode() = VerificationCode(
        id = id.value.toString(),
        code = code
    )

    companion object : UUIDEntityClass<VerificationCodeEntity>(VerificationCodeTable)
}

data class VerificationCode(
    val id: String,
    val code: String
)