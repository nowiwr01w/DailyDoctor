package nowiwr01p.daily.doctor.database.table.sign_up_token

import nowiwr01p.daily.doctor.database.table.user.UserTable
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.UUID

object SignUpTokenTable: UUIDTable("singUpToken") {
    val token = varchar("signUpToken", 64)
    val email = reference("email", UserTable.email)
}

class SignUpTokenEntity(userId: EntityID<UUID>): UUIDEntity(userId) {
    var token by SignUpTokenTable.token
    var email by SignUpTokenTable.email
    companion object : UUIDEntityClass<SignUpTokenEntity>(SignUpTokenTable)
}