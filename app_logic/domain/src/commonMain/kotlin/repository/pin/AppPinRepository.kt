package repository.pin

import com.nowiwr01p.model.api.request.pin.ChangePinCodeRequest
import com.nowiwr01p.model.api.request.pin.CheckPinCodeRequest
import com.nowiwr01p.model.api.request.pin.CreatePinCodeRequest
import com.nowiwr01p.model.api.response.token.TokenResponse

interface AppPinRepository {
    suspend fun createPinCode(request: CreatePinCodeRequest): TokenResponse
    suspend fun checkPinCode(request: CheckPinCodeRequest): TokenResponse
    suspend fun changePinCode(request: ChangePinCodeRequest): TokenResponse
    suspend fun deletePinCode()
}