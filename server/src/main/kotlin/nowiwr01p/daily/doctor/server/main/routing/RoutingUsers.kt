package nowiwr01p.daily.doctor.server.main.routing

import com.nowiwr01p.model.usecase.execute
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import nowiwr01p.daily.doctor.server.domain.usecase.GetServerUsersUseCase

class RoutingUsers(
    private val getServerUsersUseCase: GetServerUsersUseCase
) {
    fun getUsers(route: Route) = route.get("/users") {
        call.respond(getServerUsersUseCase.execute())
    }
}