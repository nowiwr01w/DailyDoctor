package nowiwr01p.daily.doctor.server.main.user

import com.nowiwr01p.model.User
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ResultRow

object UserTable: IntIdTable("users") {
    val name = varchar("name", 512)
}

fun ResultRow.toUser() = User(
    id = get(UserTable.id).value,
    name = get(UserTable.name)
)