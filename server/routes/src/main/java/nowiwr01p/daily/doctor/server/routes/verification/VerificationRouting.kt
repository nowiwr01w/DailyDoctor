package nowiwr01p.daily.doctor.server.routes.verification

import com.nowiwr01p.model.api.request.verification.CheckVerificationCodeRequest
import com.nowiwr01p.model.api.request.verification.SendVerificationCodeRequest
import com.nowiwr01p.model.api.route.VerificationRoutes.CheckVerificationCodeRoute
import com.nowiwr01p.model.api.route.VerificationRoutes.SendVerificationCodeRoute
import com.nowiwr01p.model.extensions.runCatchingApp
import io.ktor.server.application.call
import io.ktor.server.request.receiveNullable
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import nowiwr01p.daily.doctor.server.domain.usecase.verification.ServerCheckVerificationCodeUseCase
import nowiwr01p.daily.doctor.server.domain.usecase.verification.ServerSendVerificationCodeUseCase
import nowiwr01p.daily.doctor.server.routes.BaseRouting

class VerificationRouting(
    private val serverSendVerificationCodeUseCase: ServerSendVerificationCodeUseCase,
    private val serverCheckVerificationCodeUseCase: ServerCheckVerificationCodeUseCase
): BaseRouting() {

    fun sendVerificationCode(route: Route) = route.post(SendVerificationCodeRoute.route) {
        runCatchingApp {
            val request = call.receiveNullable<SendVerificationCodeRequest>() ?: run {
                sendNoRequestError<SendVerificationCodeRequest>()
                return@post
            }
            serverSendVerificationCodeUseCase.execute(request)
        }.onSuccess { sendVerificationCodeResponse ->
            respondWithSuccessModel(sendVerificationCodeResponse)
        }.onFailure { error ->
            sendInternalError(error.message)
        }
    }

    fun checkVerificationCode(route: Route) = route.post(CheckVerificationCodeRoute.route) {
        runCatchingApp {
            val request = call.receiveNullable<CheckVerificationCodeRequest>() ?: run {
                sendNoRequestError<CheckVerificationCodeRequest>()
                return@post
            }
            serverCheckVerificationCodeUseCase.execute(request)
        }.onSuccess { checkVerificationCodeResponse ->
            respondWithSuccessModel(checkVerificationCodeResponse)
        }.onFailure { error ->
            sendInternalError(error.message)
        }
    }
}