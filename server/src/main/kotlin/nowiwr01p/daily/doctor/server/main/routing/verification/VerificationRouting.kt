package nowiwr01p.daily.doctor.server.main.routing.verification

import com.nowiwr01p.model.api.request.verification.VerificationCodeRequest
import com.nowiwr01p.model.api.route.VerificationRoutes.SendVerificationCodeRoute
import io.ktor.server.request.receiveNullable
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import nowiwr01p.daily.doctor.server.domain.usecase.verification.ServerSendVerificationCodeUseCase
import nowiwr01p.daily.doctor.server.main.routing.BaseRouting

class VerificationRouting(
    private val serverSendVerificationCodeUseCase: ServerSendVerificationCodeUseCase
): BaseRouting() {

    fun sendVerificationCode(route: Route) = route.post(SendVerificationCodeRoute.route) {
        runCatching {
            val request = call.receiveNullable<VerificationCodeRequest>() ?: run {
                sendNoRequestError<VerificationCodeRequest>()
            }
            serverSendVerificationCodeUseCase.execute(request)
        }.onSuccess { verificationCodeResponse ->
            call.respond(verificationCodeResponse)
        }.onFailure { error ->
            sendInternalError(error.message)
        }
    }
}