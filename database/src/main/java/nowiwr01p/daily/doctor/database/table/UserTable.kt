package nowiwr01p.daily.doctor.database.table

import com.nowiwr01p.model.model.User
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object UserTable: Table("users") {
    val id = varchar("id", 512)
    val name = varchar("name", 64)
}

fun ResultRow.toUser() = User(
    id = get(UserTable.id),
    name = get(UserTable.name)
)