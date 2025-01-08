package nowiwr01p.daily.doctor.database.domain.repository.pin

import com.nowiwr01p.model.api.request.pin.ChangePinCodeRequest
import com.nowiwr01p.model.api.request.pin.CheckPinCodeRequest
import com.nowiwr01p.model.api.request.pin.CreatePinCodeRequest
import com.nowiwr01p.model.api.response.token.AuthBearerTokenResponse
import com.nowiwr01p.model.api.response.token.TokenResponse

interface DatabasePinCodeRepository {
    suspend fun isPinCodeSet(pinCodeToken: String): Boolean
    suspend fun createPinCode(
        authToken: String,
        request: CreatePinCodeRequest
    ): AuthBearerTokenResponse
    suspend fun checkPinCode(
        authToken: String,
        request: CheckPinCodeRequest
    ): AuthBearerTokenResponse
    suspend fun changePinCode(request: ChangePinCodeRequest): TokenResponse
    suspend fun deletePinCode()
}