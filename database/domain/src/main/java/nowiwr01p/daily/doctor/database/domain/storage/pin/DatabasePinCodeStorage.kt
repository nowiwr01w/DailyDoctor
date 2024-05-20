package nowiwr01p.daily.doctor.database.domain.storage.pin

interface DatabasePinCodeStorage {
    fun createPinCode()
    fun checkPinCode()
    fun changePinCode()
    fun deletePinCode()
}