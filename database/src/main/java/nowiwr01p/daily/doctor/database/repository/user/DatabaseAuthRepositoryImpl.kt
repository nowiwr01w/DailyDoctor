package nowiwr01p.daily.doctor.database.repository.user

import com.nowiwr01p.model.api.request.auth.BaseAuthRequest
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
        val existedUser = getExistedUser(request)?.toUser()
        existedUser ?: throw IllegalStateException("Wrong email or password.")
    }

    override suspend fun signUp(request: SignUpRequest) = transaction {
        if (getExistedUser(request) != null) {
            throw IllegalArgumentException("This email cannot be used for registration.")
        }
        val insertedUser = UserEntity.new {
            email = request.email
            password = request.password
            agreementVersion = request.agreementVersion
            isEmailVerified = false
        }
        val insertedToken = SignUpTokenEntity.new {
            email = insertedUser.email
            token = "1234"
        }
        SignUpResponse(
            email = insertedUser.email,
            timeStamp = System.currentTimeMillis(),
            pinCodeConfirmationToken = insertedToken.token
        )
    }

    private fun getExistedUser(request: BaseAuthRequest) = UserEntity
        .find { UserTable.email eq request.email }
        .firstOrNull()
}