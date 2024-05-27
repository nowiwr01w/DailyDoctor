package nowiwr01p.daily.doctor.database.data.storage.user

import com.nowiwr01p.model.api.request.auth.SignUpRequest
import nowiwr01p.daily.doctor.database.domain.storage.user.DatabaseUserStorage
import nowiwr01p.daily.doctor.database.tables.table.user.UserEntity
import nowiwr01p.daily.doctor.database.tables.table.user.UserTable
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseUserStorageImpl: DatabaseUserStorage {

    override fun createUser(request: SignUpRequest) = transaction {
        UserEntity.new {
            email = request.email
            password = request.password
            pinCodeToken = ""
            isEmailVerified = false
            agreementVersion = request.agreementVersion
        }.toUser()
    }

    override fun getUser(email: String) = transaction {
        UserEntity.find { UserTable.email eq email }
            .firstOrNull()
            ?.toUser()
    }

    override fun setUserVerified(email: String) = updateUser(email) { entity ->
        entity.isEmailVerified = true
    }

    override fun setUserPinCodeToken(email: String, token: String) = updateUser(email) { entity ->
        entity.pinCodeToken = token
    }

    private fun updateUser(email: String, callback: (UserEntity) -> Unit) = transaction {
        UserEntity.findSingleByAndUpdate(UserTable.email eq email) { entity ->
            callback(entity)
        }?.toUser()
    }
}