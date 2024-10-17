package nowiwr01p.daily.doctor.database.tables.table.user

import com.nowiwr01p.model.model.User
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.UUID

object UserTable : UUIDTable("users") {
    val phone = varchar("phone", 64).uniqueIndex()
    val password = varchar("password", 64)
    val pinCodeToken = varchar("pinCodeToken", 32)
    val agreementVersion = integer("agreementVersion")
    val isPhoneVerified = bool("isPhoneVerified")
}

class UserEntity(id: EntityID<UUID>) : UUIDEntity(id) {

    var phone by UserTable.phone
    var password by UserTable.password
    var pinCodeToken by UserTable.pinCodeToken
    var agreementVersion by UserTable.agreementVersion
    var isPhoneVerified by UserTable.isPhoneVerified

    fun toUser() = User(
        id = id.value.toString(),
        phone = phone,
        password = password,
        pinCodeToken = pinCodeToken,
        agreementVersion = agreementVersion,
        isPhoneVerified = isPhoneVerified
    )

    companion object : UUIDEntityClass<UserEntity>(UserTable)
}