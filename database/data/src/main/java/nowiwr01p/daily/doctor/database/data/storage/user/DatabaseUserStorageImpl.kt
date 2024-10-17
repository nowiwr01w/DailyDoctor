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
            phone = request.phone
            password = request.password
            pinCodeToken = ""
            isPhoneVerified = false
            agreementVersion = request.agreementVersion
        }.toUser()
    }

    override fun getUser(phone: String) = transaction {
        UserEntity.find { UserTable.phone eq phone }
            .firstOrNull()
            ?.toUser()
    }

    override fun setUserVerified(phone: String) = updateUser(phone) { entity ->
        entity.isPhoneVerified = true
    }

    override fun setUserPinCodeToken(phone: String, token: String) = updateUser(phone) { entity ->
        entity.pinCodeToken = token
    }

    private fun updateUser(phone: String, callback: (UserEntity) -> Unit) = transaction {
        UserEntity.findSingleByAndUpdate(UserTable.phone eq phone) { entity ->
            callback(entity)
        }?.toUser()
    }
}