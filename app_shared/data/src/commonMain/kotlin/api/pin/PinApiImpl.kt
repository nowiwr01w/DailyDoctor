package api.pin

import api.BaseApi
import com.nowiwr01p.model.api.request.pin.ChangePinCodeRequest
import com.nowiwr01p.model.api.request.pin.CheckPinCodeRequest
import com.nowiwr01p.model.api.request.pin.CreatePinCodeRequest
import com.nowiwr01p.model.api.response.token.TokenResponse
import com.nowiwr01p.model.api.route.PinCodeRoutes.ChangePinRoute
import com.nowiwr01p.model.api.route.PinCodeRoutes.CheckPinRoute
import com.nowiwr01p.model.api.route.PinCodeRoutes.CreatePinRoute
import com.nowiwr01p.model.api.route.PinCodeRoutes.DeletePinRoute

class PinApiImpl: BaseApi(), PinApi {

    override suspend fun createPinCode(request: CreatePinCodeRequest) = postHttp<TokenResponse>(
        route = CreatePinRoute,
        requestBody = request
    )

    override suspend fun checkPinCode(request: CheckPinCodeRequest) = postHttp<TokenResponse>(
        route = CheckPinRoute,
        requestBody = request
    )

    override suspend fun changePinCode(request: ChangePinCodeRequest) = postHttp<TokenResponse>(
        route = ChangePinRoute,
        requestBody = request
    )

    override suspend fun deletePinCode() = deleteHttp<Unit>(route = DeletePinRoute)
}