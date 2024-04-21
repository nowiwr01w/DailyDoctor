package nowiwr01p.daily.doctor.server.main.routing

import com.nowiwr01p.model.api.request.CreateUserRequest
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import nowiwr01p.daily.doctor.server.domain.usecase.CreateServerUserUseCase

class RoutingUsers(
    private val createServerUserUseCase: CreateServerUserUseCase
) {
    /**
     * curl -X POST http://0.0.0.0:8080/createUser -H "Content-Type: application/json" -d '{"password": "123", "email": "nowiwr01p@pm.me"}'
     */
    fun createUser(route: Route) = route.post("/createUser") {
        val createUserRequest = call.receive<CreateUserRequest>()
        runCatching {
            createServerUserUseCase.execute(createUserRequest)
        }.onSuccess { apiUser ->
            call.respond(apiUser)
        }.onFailure { error ->
            call.respond(error.message.orEmpty())
        }
    }
}