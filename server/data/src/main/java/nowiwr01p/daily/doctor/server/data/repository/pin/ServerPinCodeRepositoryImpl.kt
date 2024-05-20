package nowiwr01p.daily.doctor.server.data.repository.pin

import nowiwr01p.daily.doctor.database.domain.repository.pin.DatabasePinCodeRepository
import nowiwr01p.daily.doctor.server.domain.repository.pin.ServerPinCodeRepository

class ServerPinCodeRepositoryImpl(
    private val repository: DatabasePinCodeRepository
): ServerPinCodeRepository {

    override suspend fun createPinCode() {
        repository.createPinCode()
    }

    override suspend fun checkPinCode() {
        repository.checkPinCode()
    }

    override suspend fun changePinCode() {
        repository.changePinCode()
    }

    override suspend fun deletePinCode() {
        repository.deletePinCode()
    }
}