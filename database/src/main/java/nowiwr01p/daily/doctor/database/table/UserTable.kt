package nowiwr01p.daily.doctor.database.table

import com.nowiwr01p.model.model.User
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.UUID

object UserTable : UUIDTable("users") {
    val email = varchar("email", 64).uniqueIndex()
}

class UserEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    var email by UserTable.email

    fun toUser() = User(id.value.toString(), email)

    companion object : UUIDEntityClass<UserEntity>(UserTable)
}