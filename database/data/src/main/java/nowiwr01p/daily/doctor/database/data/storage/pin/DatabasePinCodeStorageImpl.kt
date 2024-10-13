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

    override fun createPinCode(request: CreatePinCodeRequest) = transaction {
        PinCodeEntity.new {
            code = request.code
            pinCodeToken = request.pinCodeToken
        }
        AuthBearerTokenResponse("Типа токен))") // TODO: Send auth token
    }

    override fun checkPinCode(request: CheckPinCodeRequest) = transaction {
        val pinCodeEntity = PinCodeEntity
            .find { PinCodeTable.pinCodeToken eq request.checkPinCodeToken }
            .firstOrNull()
        println("Zhopa, request.checkPinCodeToken = ${request.checkPinCodeToken}")
        println("Zhopa, db = ${pinCodeEntity?.code}, requested = ${request.code}")
        when {
            pinCodeEntity == null || request.code != pinCodeEntity.code -> null
            else -> AuthBearerTokenResponse("Типа токен))")
        }
    }

    override fun changePinCode() {

    }

    override fun deletePinCode() {

    }
}