package api.pin

import api.Api

interface PinApi: Api {
    suspend fun createPinCode()
    suspend fun checkPinCode()
    suspend fun changePinCode()
    suspend fun deletePinCode()
}