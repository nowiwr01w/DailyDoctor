package nowiwr01p.daily.doctor.server.main.routing.auth

import com.nowiwr01p.model.api.request.auth.SignInRequest
import com.nowiwr01p.model.api.request.auth.SignUpRequest
import com.nowiwr01p.model.api.route.AuthRoutes.SingInRoute
import com.nowiwr01p.model.api.route.AuthRoutes.SingUpRoute
import io.ktor.server.request.receiveNullable
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import nowiwr01p.daily.doctor.server.domain.usecase.ServerSignInUseCase
import nowiwr01p.daily.doctor.server.domain.usecase.ServerSignUpUseCase
import nowiwr01p.daily.doctor.server.main.routing.BaseRouting

class AuthRouting(
    private val serverSignInUseCase: ServerSignInUseCase,
    private val serverSignUpUseCase: ServerSignUpUseCase
): BaseRouting() {

    fun signIn(route: Route) = route.post(SingInRoute.route) {
        runCatching {
            val signInRequest = call.receiveNullable<SignInRequest>() ?: run {
                sendNoRequestError<SignInRequest>()
            }
            serverSignInUseCase.execute(signInRequest)
        }.onSuccess { apiUser ->
            call.respond(apiUser)
        }.onFailure { error ->
            sendInternalError(error.message)
        }
    }

    fun signUp(route: Route) = route.post(SingUpRoute.route) {
        runCatching {
            val signUpRequest = call.receiveNullable<SignUpRequest>() ?: run {
                sendNoRequestError<SignUpRequest>()
            }
            serverSignUpUseCase.execute(signUpRequest)
        }.onSuccess { signUpResponse ->
            call.respond(signUpResponse)
        }.onFailure { error ->
            sendInternalError(error.message)
        }
    }
}