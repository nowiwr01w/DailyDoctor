package nowiwr01p.daily.doctor.database.domain.repository.pin

interface DatabasePinCodeRepository {
    suspend fun createPinCode()
    suspend fun checkPinCode()
    suspend fun changePinCode()
    suspend fun deletePinCode()
}