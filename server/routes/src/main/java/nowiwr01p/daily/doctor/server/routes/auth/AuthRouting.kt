package nowiwr01p.daily.doctor.server.routes.auth

import com.nowiwr01p.model.api.request.auth.SignInRequest
import com.nowiwr01p.model.api.request.auth.SignUpRequest
import com.nowiwr01p.model.api.route.AuthRoutes.SingInRoute
import com.nowiwr01p.model.api.route.AuthRoutes.SingUpRoute
import com.nowiwr01p.model.extensions.runCatchingApp
import io.ktor.server.application.call
import io.ktor.server.request.receiveNullable
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import nowiwr01p.daily.doctor.server.domain.usecase.auth.ServerSignInUseCase
import nowiwr01p.daily.doctor.server.domain.usecase.auth.ServerSignUpUseCase
import nowiwr01p.daily.doctor.server.routes.BaseRouting

class AuthRouting(
    private val serverSignInUseCase: ServerSignInUseCase,
    private val serverSignUpUseCase: ServerSignUpUseCase
): BaseRouting() {

    fun signIn(route: Route) = route.post(SingInRoute.route) {
        runCatchingApp {
            val signInRequest = getRequestBody<SignInRequest>() ?: return@post
            serverSignInUseCase.execute(signInRequest)
        }.onSuccess { tokenResponse ->
            respondWithSuccessModel(tokenResponse)
        }.onFailure { error ->
            sendInternalError(error.message)
        }
    }

    fun signUp(route: Route) = route.post(SingUpRoute.route) {
        runCatchingApp {
            val signUpRequest = getRequestBody<SignUpRequest>() ?: return@post
            serverSignUpUseCase.execute(signUpRequest)
        }.onSuccess { tokenResponse ->
            respondWithSuccessModel(tokenResponse)
        }.onFailure { error ->
            sendInternalError(error.message)
        }
    }
}