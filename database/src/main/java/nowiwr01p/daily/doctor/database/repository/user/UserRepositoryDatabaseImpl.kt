package nowiwr01p.daily.doctor.database.repository.user

import com.nowiwr01p.model.api.request.CreateUserRequest
import com.nowiwr01p.model.repository.BaseRepository
import nowiwr01p.daily.doctor.database.table.UserTable
import nowiwr01p.daily.doctor.database.table.toUser
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID

class UserRepositoryDatabaseImpl: BaseRepository(), UserRepositoryDatabase {

    override suspend fun createUser(request: CreateUserRequest) = transaction {
        val isUserAlreadyExists = UserTable.selectAll()
            .where { UserTable.email eq request.email }
            .singleOrNull() != null
        if (isUserAlreadyExists) {
            throw IllegalArgumentException("Пользователь с таким email уже существует.")
        }
        val insertedUserId = UserTable.insertAndGetId { tableUser ->
            tableUser[email] = request.email
        }
        UserTable.selectAll().where { UserTable.id eq insertedUserId }
            .firstOrNull()
            ?.toUser() ?: buildError()
    }

    override suspend fun getUsersById(id: String) = transaction {
        UserTable.selectAll().where { UserTable.id eq UUID.fromString(id) }
            .firstOrNull()
            ?.toUser() ?: buildError()
    }
}