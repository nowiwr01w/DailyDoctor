package nowiwr01p.daily.doctor.server.domain.repository.pin

interface ServerPinCodeRepository {
    suspend fun createPinCode()
    suspend fun checkPinCode()
    suspend fun changePinCode()
    suspend fun deletePinCode()
}