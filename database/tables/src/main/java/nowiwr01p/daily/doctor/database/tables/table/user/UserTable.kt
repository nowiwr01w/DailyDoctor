package nowiwr01p.daily.doctor.database.tables.table.user

import com.nowiwr01p.model.model.User
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.UUID

object UserTable : UUIDTable("users") {
    val email = varchar("email", 64).uniqueIndex()
    val password = varchar("password", 64)
    val agreementVersion = integer("agreementVersion")
    val isEmailVerified = bool("isEmailVerified")
}

class UserEntity(id: EntityID<UUID>) : UUIDEntity(id) {

    var email by UserTable.email
    var password by UserTable.password
    var agreementVersion by UserTable.agreementVersion
    var isEmailVerified by UserTable.isEmailVerified

    fun toUser() = User(
        id = id.value.toString(),
        email = email,
        password = password,
        agreementVersion = agreementVersion,
        isEmailVerified = isEmailVerified
    )

    companion object : UUIDEntityClass<UserEntity>(UserTable)
}