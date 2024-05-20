package nowiwr01p.daily.doctor.database.data.repository.pin

import nowiwr01p.daily.doctor.database.domain.repository.pin.DatabasePinCodeRepository
import nowiwr01p.daily.doctor.database.domain.storage.pin.DatabasePinCodeStorage

class DatabasePinCodeRepositoryImpl(
    private val storage: DatabasePinCodeStorage
): DatabasePinCodeRepository {

    override suspend fun createPinCode() {
        storage.createPinCode()
    }

    override suspend fun checkPinCode() {
        storage.checkPinCode()
    }

    override suspend fun changePinCode() {
        storage.changePinCode()
    }

    override suspend fun deletePinCode() {
        storage.deletePinCode()
    }
}