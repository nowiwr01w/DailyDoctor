package nowiwr01p.daily.doctor.database.repository.user

import com.nowiwr01p.model.api.request.SignInRequest
import com.nowiwr01p.model.api.request.SignUpRequest
import com.nowiwr01p.model.model.User
import com.nowiwr01p.model.repository.BaseRepository
import nowiwr01p.daily.doctor.database.table.UserTable
import nowiwr01p.daily.doctor.database.table.toUser
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseAuthRepositoryImpl: BaseRepository(), DatabaseAuthRepository {

    override suspend fun signIn(request: SignInRequest): User {
        TODO("Not yet implemented")
    }

    override suspend fun signUp(request: SignUpRequest) = transaction {
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
}