package nowiwr01p.daily.doctor.database.storage.user

import com.nowiwr01p.model.api.request.auth.SignUpRequest
import nowiwr01p.daily.doctor.database.table.user.UserEntity
import nowiwr01p.daily.doctor.database.table.user.UserTable
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseUserStorageImpl: DatabaseUserStorage {

    override fun createUser(request: SignUpRequest) = transaction {
        UserEntity.new {
            email = request.email
            password = request.password
            agreementVersion = request.agreementVersion
            isEmailVerified = false
        }.toUser()
    }

    override fun getUser(email: String) = transaction {
        UserEntity.find { UserTable.email eq email }
            .firstOrNull()
            ?.toUser()
    }

    override fun setVerificationStatus(email: String) = updateUser(email) { entity ->
        entity.isEmailVerified = true
    }

    private fun updateUser(email: String, callback: (UserEntity) -> Unit) = transaction {
        UserEntity.findSingleByAndUpdate(UserTable.email eq email) { entity ->
            callback(entity)
        }?.toUser()
    }
}