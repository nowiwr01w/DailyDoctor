package nowiwr01p.daily.doctor.server.main.routing

import com.nowiwr01p.model.api.request.SignUpRequest
import io.ktor.server.request.receiveNullable
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import nowiwr01p.daily.doctor.server.domain.usecase.ServerSignUpUseCase

class RoutingAuth(
    private val serverSignUpUseCase: ServerSignUpUseCase
) {
    /**
     * curl -X POST http://0.0.0.0:8080/createUser -H "Content-Type: application/json" -d '{"password": "123", "email": "nowiwr01p@pm.me"}'
     */
    fun signUp(route: Route) = route.post("v1/auth/signUp") {
        runCatching {
            val signUpRequest = call.receiveNullable<SignUpRequest>() ?: run {
                throw IllegalStateException("Missing CreateUserRequest")
            }
            serverSignUpUseCase.execute(signUpRequest)
        }.onSuccess { apiUser ->
            call.respond(apiUser)
        }.onFailure { error ->
            call.respond(error.message.orEmpty())
        }
    }
}