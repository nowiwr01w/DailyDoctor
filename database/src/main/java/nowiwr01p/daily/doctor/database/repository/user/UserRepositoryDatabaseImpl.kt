package nowiwr01p.daily.doctor.database.repository.user

import com.nowiwr01p.model.repository.BaseRepository
import nowiwr01p.daily.doctor.database.table.UserTable
import nowiwr01p.daily.doctor.database.table.toUser
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class UserRepositoryDatabaseImpl: BaseRepository(), UserRepositoryDatabase {

    override suspend fun getUsers() = transaction {
        UserTable.selectAll().map { it.toUser() }.toList()
    }

    override suspend fun getUsersById(id: String) = transaction {
        UserTable.selectAll().where { UserTable.id eq id }
            .firstOrNull()
            ?.toUser() ?: buildError()
    }
}