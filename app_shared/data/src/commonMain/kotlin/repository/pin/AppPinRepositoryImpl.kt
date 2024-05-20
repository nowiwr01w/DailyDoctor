package repository.pin

import api.pin.PinApi
import com.nowiwr01p.model.api.request.pin.ChangePinCodeRequest
import com.nowiwr01p.model.api.request.pin.CheckPinCodeRequest
import com.nowiwr01p.model.api.request.pin.CreatePinCodeRequest
import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import kotlinx.coroutines.withContext

class AppPinRepositoryImpl(
    private val api: PinApi,
    private val dispatchers: AppDispatchers
): AppPinRepository {

    override suspend fun createPinCode(request: CreatePinCodeRequest) = withContext(dispatchers.io) {
        api.createPinCode(request)
    }

    override suspend fun checkPinCode(request: CheckPinCodeRequest) = withContext(dispatchers.io) {
        api.checkPinCode(request)
    }

    override suspend fun changePinCode(request: ChangePinCodeRequest) = withContext(dispatchers.io) {
        api.changePinCode(request)
    }

    override suspend fun deletePinCode() = withContext(dispatchers.io) {
        api.deletePinCode()
    }
}