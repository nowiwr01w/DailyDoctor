package repository.pin

import api.pin.PinApi
import com.nowiwr01p.model.api.request.pin.ChangePinCodeRequest
import com.nowiwr01p.model.api.request.pin.CheckPinCodeRequest
import com.nowiwr01p.model.api.request.pin.CreatePinCodeRequest
import repository.AppBaseRepository

class AppPinRepositoryImpl(
    private val api: PinApi
): AppBaseRepository(), AppPinRepository {

    override suspend fun createPinCode(request: CreatePinCodeRequest) = io {
        api.createPinCode(request)
    }

    override suspend fun checkPinCode(request: CheckPinCodeRequest) = io {
        api.checkPinCode(request)
    }

    override suspend fun changePinCode(request: ChangePinCodeRequest) = io {
        api.changePinCode(request)
    }

    override suspend fun deletePinCode() = io {
        api.deletePinCode()
    }
}