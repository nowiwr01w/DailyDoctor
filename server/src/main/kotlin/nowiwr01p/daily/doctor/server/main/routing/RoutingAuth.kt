package nowiwr01p.daily.doctor.server.main.routing

import com.nowiwr01p.model.api.request.SignInRequest
import com.nowiwr01p.model.api.request.SignUpRequest
import io.ktor.server.request.receiveNullable
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import nowiwr01p.daily.doctor.server.domain.usecase.ServerSignInUseCase
import nowiwr01p.daily.doctor.server.domain.usecase.ServerSignUpUseCase

class RoutingAuth(
    private val serverSignInUseCase: ServerSignInUseCase,
    private val serverSignUpUseCase: ServerSignUpUseCase
) {
    fun signIn(route: Route) = route.post("v1/auth/signIn") {
        runCatching {
            val signInRequest = call.receiveNullable<SignInRequest>()!!
            serverSignInUseCase.execute(signInRequest)
        }.onSuccess { apiUser ->
            call.respond(apiUser)
        }.onFailure { error ->
            call.respond(mapOf("error" to error.message.orEmpty()))
        }
    }

    fun signUp(route: Route) = route.post("v1/auth/signUp") {
        runCatching {
            val signUpRequest = call.receiveNullable<SignUpRequest>()!!
            serverSignUpUseCase.execute(signUpRequest)
        }.onSuccess { apiUser ->
            call.respond(apiUser)
        }.onFailure { error ->
            call.respond(mapOf("error" to error.message.orEmpty()))
        }
    }
}