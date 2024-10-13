package nowiwr01p.daily.doctor.database.data.storage.pin

import com.nowiwr01p.model.api.request.pin.CheckPinCodeRequest
import com.nowiwr01p.model.api.request.pin.CreatePinCodeRequest
import com.nowiwr01p.model.api.response.token.AuthBearerTokenResponse
import nowiwr01p.daily.doctor.database.domain.storage.pin.DatabasePinCodeStorage
import nowiwr01p.daily.doctor.database.tables.table.pin.PinCodeEntity
import nowiwr01p.daily.doctor.database.tables.table.pin.PinCodeTable
import org.jetbrains.exposed.sql.transactions.transaction

class DatabasePinCodeStorageImpl: DatabasePinCodeStorage {

    override fun isPinCodeSet(pinCodeToken: String) = transaction {
        val pinCodeEntity = PinCodeEntity
            .find { PinCodeTable.pinCodeToken eq pinCodeToken }
            .firstOrNull()
        pinCodeEntity != null && pinCodeEntity.code.isNotEmpty()
    }

    override fun createPinCode(authToken: String, request: CreatePinCodeRequest) = transaction {
        PinCodeEntity.new {
            code = request.code
            pinCodeToken = request.pinCodeToken
        }
        AuthBearerTokenResponse(authToken)
    }

    override fun checkPinCode(authToken: String, request: CheckPinCodeRequest) = transaction {
        val pinCodeEntity = PinCodeEntity
            .find { PinCodeTable.pinCodeToken eq request.checkPinCodeToken }
            .firstOrNull()
        when {
            pinCodeEntity == null || request.code != pinCodeEntity.code -> null
            else -> AuthBearerTokenResponse(authToken)
        }
    }

    override fun changePinCode() {

    }

    override fun deletePinCode() {

    }
}