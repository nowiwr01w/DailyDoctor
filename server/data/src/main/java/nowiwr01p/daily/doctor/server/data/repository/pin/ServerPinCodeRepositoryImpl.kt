package nowiwr01p.daily.doctor.server.data.repository.pin

import com.nowiwr01p.model.api.request.pin.ChangePinCodeRequest
import com.nowiwr01p.model.api.request.pin.CheckPinCodeRequest
import com.nowiwr01p.model.api.request.pin.CreatePinCodeRequest
import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import kotlinx.coroutines.withContext
import nowiwr01p.daily.doctor.database.domain.repository.pin.DatabasePinCodeRepository
import nowiwr01p.daily.doctor.server.domain.repository.pin.ServerPinCodeRepository

class ServerPinCodeRepositoryImpl(
    private val dispatchers: AppDispatchers,
    private val repository: DatabasePinCodeRepository
): ServerPinCodeRepository {

    override suspend fun createPinCode(request: CreatePinCodeRequest) = withContext(dispatchers.io) {
        repository.createPinCode(request)
    }

    override suspend fun checkPinCode(request: CheckPinCodeRequest) = withContext(dispatchers.io) {
        repository.checkPinCode(request)
    }

    override suspend fun changePinCode(request: ChangePinCodeRequest) = withContext(dispatchers.io) {
        repository.changePinCode(request)
    }

    override suspend fun deletePinCode() = withContext(dispatchers.io) {
        repository.deletePinCode()
    }
}