package repository.pin

interface AppPinRepository {
    suspend fun createPinCode()
    suspend fun checkPinCode()
    suspend fun changePinCode()
    suspend fun deletePinCode()
}