package nowiwr01p.daily.doctor.server.main.routing

import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import nowiwr01p.daily.doctor.server.domain.usecase.GetServerUserByIdUseCase

class RoutingUser(
    private val getServerUserByIdUseCase: GetServerUserByIdUseCase
) {
    fun getUserById(route: Route) = route.get("/user/{id}") {
        val requestedUserId = call.parameters["id"] ?: throw IllegalStateException("No userId provided")
        if (requestedUserId.isEmpty()) {
            throw IllegalStateException("Bad request")
        }
        call.respond(getServerUserByIdUseCase.execute(requestedUserId))
    }
}