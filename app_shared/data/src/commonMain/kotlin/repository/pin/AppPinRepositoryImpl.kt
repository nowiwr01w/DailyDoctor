package repository.pin

import api.pin.PinApi
import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import kotlinx.coroutines.withContext

class AppPinRepositoryImpl(
    private val api: PinApi,
    private val dispatchers: AppDispatchers
): AppPinRepository {

    override suspend fun createPinCode() = withContext(dispatchers.io) {
        api.createPinCode()
    }

    override suspend fun checkPinCode() = withContext(dispatchers.io) {
        api.checkPinCode()
    }

    override suspend fun changePinCode() = withContext(dispatchers.io) {
        api.changePinCode()
    }

    override suspend fun deletePinCode() = withContext(dispatchers.io) {
        api.deletePinCode()
    }
}