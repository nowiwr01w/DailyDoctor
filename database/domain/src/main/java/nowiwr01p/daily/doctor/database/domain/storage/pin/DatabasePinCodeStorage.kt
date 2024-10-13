package nowiwr01p.daily.doctor.database.domain.storage.pin

import com.nowiwr01p.model.api.request.pin.CheckPinCodeRequest
import com.nowiwr01p.model.api.request.pin.CreatePinCodeRequest
import com.nowiwr01p.model.api.response.token.AuthBearerTokenResponse
import com.nowiwr01p.model.api.response.token.TokenResponse

interface DatabasePinCodeStorage {
    fun isPinCodeSet(pinCodeToken: String): Boolean
    fun createPinCode(request: CreatePinCodeRequest): TokenResponse // TODO: Send auth token
    fun checkPinCode(request: CheckPinCodeRequest): AuthBearerTokenResponse?
    fun changePinCode()
    fun deletePinCode()
}