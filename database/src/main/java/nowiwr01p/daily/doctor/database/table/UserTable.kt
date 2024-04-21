package nowiwr01p.daily.doctor.database.table

import com.nowiwr01p.model.model.User
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ResultRow

object UserTable: UUIDTable("users") {
    val email = varchar("email", 64).uniqueIndex()
}

fun ResultRow.toUser() = User(
    id = get(UserTable.id).value.toString(),
    email = get(UserTable.email)
)