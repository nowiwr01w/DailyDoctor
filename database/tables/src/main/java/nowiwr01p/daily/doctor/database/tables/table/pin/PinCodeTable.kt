package nowiwr01p.daily.doctor.database.tables.table.pin

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.UUID

object PinCodeTable : UUIDTable("pin_codes") {
    val code = varchar("code", 6)
    val timestamp = long("timestamp")
    val pinCodeToken = varchar("pinCodeToken", 32).uniqueIndex()
}

class PinCodeEntity(id: EntityID<UUID>) : UUIDEntity(id) {

    var code by PinCodeTable.code
    var timestamp by PinCodeTable.timestamp
    var verificationToken by PinCodeTable.pinCodeToken

    companion object : UUIDEntityClass<PinCodeEntity>(PinCodeTable)
}