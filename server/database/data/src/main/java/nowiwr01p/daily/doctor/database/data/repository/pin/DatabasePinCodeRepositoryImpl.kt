package nowiwr01p.daily.doctor.database.data.repository.pin

import com.nowiwr01p.model.api.request.pin.ChangePinCodeRequest
import com.nowiwr01p.model.api.request.pin.CheckPinCodeRequest
import com.nowiwr01p.model.api.request.pin.CreatePinCodeRequest
import com.nowiwr01p.model.api.response.token.AuthBearerTokenResponse
import com.nowiwr01p.model.api.response.token.TokenResponse
import com.nowiwr01p.model.repository.BaseRepository
import nowiwr01p.daily.doctor.database.domain.repository.pin.DatabasePinCodeRepository
import nowiwr01p.daily.doctor.database.domain.storage.pin.DatabasePinCodeStorage

class DatabasePinCodeRepositoryImpl(
    private val storage: DatabasePinCodeStorage,
): BaseRepository(), DatabasePinCodeRepository {

    override suspend fun isPinCodeSet(pinCodeToken: String): Boolean {
        return storage.isPinCodeSet(pinCodeToken)
    }

    override suspend fun createPinCode(
        authToken: String,
        request: CreatePinCodeRequest
    ): AuthBearerTokenResponse {
        return storage.createPinCode(authToken, request)
    }

    override suspend fun checkPinCode(
        authToken: String,
        request: CheckPinCodeRequest
    ): AuthBearerTokenResponse {
        return storage.checkPinCode(authToken, request) ?: buildError("Wrong pin code.")
    }

    override suspend fun changePinCode(request: ChangePinCodeRequest): TokenResponse {
        storage.changePinCode()
        TODO()
    }

    override suspend fun deletePinCode() {
        storage.deletePinCode()
        TODO()
    }
}