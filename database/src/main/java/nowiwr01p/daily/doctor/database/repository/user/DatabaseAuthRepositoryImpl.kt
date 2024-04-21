package nowiwr01p.daily.doctor.database.repository.user

import com.nowiwr01p.model.api.request.BaseAuthRequest
import com.nowiwr01p.model.api.request.SignInRequest
import com.nowiwr01p.model.api.request.SignUpRequest
import com.nowiwr01p.model.repository.BaseRepository
import nowiwr01p.daily.doctor.database.table.UserEntity
import nowiwr01p.daily.doctor.database.table.UserTable
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseAuthRepositoryImpl: BaseRepository(), DatabaseAuthRepository {

    override suspend fun signIn(request: SignInRequest) = transaction {
        val existedUser = getExistedUser(request)?.toUser()
        existedUser ?: throw IllegalStateException("Wrong email or password.")
    }

    override suspend fun signUp(request: SignUpRequest) = transaction {
        if (getExistedUser(request) != null) {
            throw IllegalArgumentException("This email cannot be used for registration.")
        }
        val insertedUser = UserEntity.new { email = request.email }
        insertedUser.toUser()
    }

    private fun getExistedUser(request: BaseAuthRequest) = UserEntity
        .find { UserTable.email eq request.email }
        .firstOrNull()
}