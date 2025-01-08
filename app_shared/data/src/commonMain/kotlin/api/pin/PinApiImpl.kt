package api.pin

import com.nowiwr01p.model.api.errors.pin.PinApiError
import com.nowiwr01p.model.api.errors.pin.PinApiError.ChangePinApiError
import com.nowiwr01p.model.api.errors.pin.PinApiError.CheckPinApiError
import com.nowiwr01p.model.api.errors.pin.PinApiError.CreatePinApiError
import com.nowiwr01p.model.api.errors.pin.PinApiError.DeletePinApiError
import com.nowiwr01p.model.api.request.pin.ChangePinCodeRequest
import com.nowiwr01p.model.api.request.pin.CheckPinCodeRequest
import com.nowiwr01p.model.api.request.pin.CreatePinCodeRequest
import com.nowiwr01p.model.api.response.token.TokenResponse
import com.nowiwr01p.model.api.route.PinCodeRoutes.ChangePinRoute
import com.nowiwr01p.model.api.route.PinCodeRoutes.CheckPinRoute
import com.nowiwr01p.model.api.route.PinCodeRoutes.CreatePinRoute
import com.nowiwr01p.model.api.route.PinCodeRoutes.DeletePinRoute
import nowiwr01p.daily.doctor.base_api_client.api.ApiClientSettings.AppApiClientSettings
import nowiwr01p.daily.doctor.base_api_client.api.BaseApi

class PinApiImpl: BaseApi<PinApiError>(AppApiClientSettings), PinApi {
    /**
     * CREATE
     */
    override suspend fun createPinCode(request: CreatePinCodeRequest) = run {
        postHttp<TokenResponse, CreatePinApiError>(
            route = CreatePinRoute,
            requestBody = request,
            error = { message -> CreatePinApiError(message) }
        )
    }

    /**
     * CHECK
     */
    override suspend fun checkPinCode(request: CheckPinCodeRequest) = run {
        postHttp<TokenResponse, CheckPinApiError>(
            route = CheckPinRoute,
            requestBody = request,
            error = { message -> CheckPinApiError(message) }
        )
    }

    /**
     * CHANGE
     */
    override suspend fun changePinCode(request: ChangePinCodeRequest) = run {
        postHttp<TokenResponse, ChangePinApiError>(
            route = ChangePinRoute,
            requestBody = request,
            error = { message -> ChangePinApiError(message) }
        )
    }

    /**
     * DELETE
     */
    override suspend fun deletePinCode() = deleteHttp<Unit, DeletePinApiError>(
        route = DeletePinRoute,
        error = { message -> DeletePinApiError(message) }
    )
}