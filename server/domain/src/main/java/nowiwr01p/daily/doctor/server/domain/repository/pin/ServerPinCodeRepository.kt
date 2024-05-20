package nowiwr01p.daily.doctor.server.domain.repository.pin

import com.nowiwr01p.model.api.request.pin.ChangePinCodeRequest
import com.nowiwr01p.model.api.request.pin.CheckPinCodeRequest
import com.nowiwr01p.model.api.request.pin.CreatePinCodeRequest
import com.nowiwr01p.model.api.response.token.TokenResponse

interface ServerPinCodeRepository {
    suspend fun createPinCode(request: CreatePinCodeRequest): TokenResponse
    suspend fun checkPinCode(request: CheckPinCodeRequest): TokenResponse
    suspend fun changePinCode(request: ChangePinCodeRequest): TokenResponse
    suspend fun deletePinCode()
}