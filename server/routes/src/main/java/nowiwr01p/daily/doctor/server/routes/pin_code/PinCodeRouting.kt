package nowiwr01p.daily.doctor.server.routes.pin_code

import com.nowiwr01p.model.api.request.pin.ChangePinCodeRequest
import com.nowiwr01p.model.api.request.pin.CheckPinCodeRequest
import com.nowiwr01p.model.api.request.pin.CreatePinCodeRequest
import com.nowiwr01p.model.api.route.PinCodeRoutes.ChangePinRoute
import com.nowiwr01p.model.api.route.PinCodeRoutes.CheckPinRoute
import com.nowiwr01p.model.api.route.PinCodeRoutes.CreatePinRoute
import com.nowiwr01p.model.api.route.PinCodeRoutes.DeletePinRoute
import com.nowiwr01p.model.usecase.execute
import io.ktor.server.application.call
import io.ktor.server.request.receiveNullable
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import nowiwr01p.daily.doctor.server.domain.usecase.pin.ServerChangePinCodeUseCase
import nowiwr01p.daily.doctor.server.domain.usecase.pin.ServerCheckPinCodeUseCase
import nowiwr01p.daily.doctor.server.domain.usecase.pin.ServerCreatePinCodeUseCase
import nowiwr01p.daily.doctor.server.domain.usecase.pin.ServerDeletePinCodeUseCase
import nowiwr01p.daily.doctor.server.routes.BaseRouting

class PinCodeRouting(
    private val createPinCodeUseCase: ServerCreatePinCodeUseCase,
    private val checkPinCodeUseCase: ServerCheckPinCodeUseCase,
    private val changePinCodeUseCase: ServerChangePinCodeUseCase,
    private val deletePinCodeUseCase: ServerDeletePinCodeUseCase
): BaseRouting() {

    fun createPinCode(route: Route) = route.post(CreatePinRoute.route) {
        val request = call.receiveNullable<CreatePinCodeRequest>() ?: run {
            sendNoRequestError<CreatePinCodeRequest>()
        }
        runCatching {
            createPinCodeUseCase.execute(request)
        }.onSuccess { tokenResponse ->
            sendStringObject(tokenResponse)
        }.onFailure { error ->
            sendInternalError(error.message)
        }
    }

    fun checkPinCode(route: Route) = route.post(CheckPinRoute.route) {
        val request = call.receiveNullable<CheckPinCodeRequest>() ?: run {
            sendNoRequestError<CheckPinCodeRequest>()
        }
        runCatching {
            checkPinCodeUseCase.execute(request)
        }.onSuccess { tokenResponse ->
            sendStringObject(tokenResponse)
        }.onFailure { error ->
            sendInternalError(error.message)
        }
    }

    fun changePinCode(route: Route) = route.post(ChangePinRoute.route) {
        val request = call.receiveNullable<ChangePinCodeRequest>() ?: run {
            sendNoRequestError<ChangePinCodeRequest>()
        }
        runCatching {
            changePinCodeUseCase.execute(request)
        }.onSuccess { tokenResponse ->
            sendStringObject(tokenResponse)
        }.onFailure { error ->
            sendInternalError(error.message)
        }
    }

    fun deletePinCode(route: Route) = route.post(DeletePinRoute.route) {
        runCatching {
            deletePinCodeUseCase.execute()
        }.onSuccess {
            sendSuccess()
        }.onFailure { error ->
            sendInternalError(error.message)
        }
    }
}