package nowiwr01p.daily.doctor.database.repository.auth

import com.nowiwr01p.model.api.request.auth.SignInRequest
import com.nowiwr01p.model.api.request.auth.SignUpRequest
import com.nowiwr01p.model.api.response.auth.SignUpResponse
import com.nowiwr01p.model.repository.BaseRepository
import nowiwr01p.daily.doctor.database.table.sign_up_token.SignUpTokenEntity
import nowiwr01p.daily.doctor.database.table.user.UserEntity
import nowiwr01p.daily.doctor.database.table.user.UserTable
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseAuthRepositoryImpl: BaseRepository(), DatabaseAuthRepository {

    override suspend fun signIn(request: SignInRequest) = transaction {
        val existedUser = getUser(request.email)
        existedUser ?: buildError("Wrong email or password.")
    }

    override suspend fun signUp(request: SignUpRequest) = transaction {
        if (getUser(request.email) != null) {
            buildError("This email cannot be used for registration.")
        }
        val insertedUser = UserEntity.new {
            email = request.email
            password = request.password
            agreementVersion = request.agreementVersion
            isEmailVerified = false
        }
        val insertedToken = SignUpTokenEntity.new {
            email = insertedUser.email
            token = "1234" // TODO: Add token
        }
        SignUpResponse(
            email = insertedUser.email,
            timeStamp = System.currentTimeMillis(),
            pinCodeConfirmationToken = insertedToken.token
        )
    }

    override fun getUser(email: String) = transaction {
        UserEntity.find { UserTable.email eq email }
            .firstOrNull()
            ?.toUser()
    }
}