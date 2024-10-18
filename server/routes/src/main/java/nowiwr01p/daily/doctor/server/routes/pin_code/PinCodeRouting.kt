package nowiwr01p.daily.doctor.server.routes.pin_code

import com.nowiwr01p.model.api.request.pin.ChangePinCodeRequest
import com.nowiwr01p.model.api.request.pin.CheckPinCodeRequest
import com.nowiwr01p.model.api.request.pin.CreatePinCodeRequest
import com.nowiwr01p.model.api.route.PinCodeRoutes.ChangePinRoute
import com.nowiwr01p.model.api.route.PinCodeRoutes.CheckPinRoute
import com.nowiwr01p.model.api.route.PinCodeRoutes.CreatePinRoute
import com.nowiwr01p.model.api.route.PinCodeRoutes.DeletePinRoute
import com.nowiwr01p.model.extensions.runCatchingApp
import com.nowiwr01p.model.usecase.execute
import io.ktor.server.application.call
import io.ktor.server.request.receiveNullable
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
            return@post
        }
        runCatchingApp {
            createPinCodeUseCase.execute(request)
        }.onSuccess { tokenResponse ->
            respondWithSuccessModel(tokenResponse)
        }.onFailure { error ->
            sendInternalError(error.message)
        }
    }

    fun checkPinCode(route: Route) = route.post(CheckPinRoute.route) {
        val request = call.receiveNullable<CheckPinCodeRequest>() ?: run {
            sendNoRequestError<CheckPinCodeRequest>()
            return@post
        }
        runCatchingApp {
            checkPinCodeUseCase.execute(request)
        }.onSuccess { tokenResponse ->
            respondWithSuccessModel(tokenResponse)
        }.onFailure { error ->
            sendInternalError(error.message)
        }
    }

    fun changePinCode(route: Route) = route.post(ChangePinRoute.route) {
        val request = call.receiveNullable<ChangePinCodeRequest>() ?: run {
            sendNoRequestError<ChangePinCodeRequest>()
            return@post
        }
        runCatchingApp {
            changePinCodeUseCase.execute(request)
        }.onSuccess { tokenResponse ->
            respondWithSuccessModel(tokenResponse)
        }.onFailure { error ->
            sendInternalError(error.message)
        }
    }

    fun deletePinCode(route: Route) = route.post(DeletePinRoute.route) {
        runCatchingApp {
            deletePinCodeUseCase.execute()
        }.onSuccess {
            respondWithSuccessMessage()
        }.onFailure { error ->
            sendInternalError(error.message)
        }
    }
}