package nowiwr01p.daily.doctor.server.main.server.plugins

import io.ktor.server.application.Application
import io.ktor.server.routing.Route
import io.ktor.server.routing.routing
import nowiwr01p.daily.doctor.server.main.routing.RoutingUser
import nowiwr01p.daily.doctor.server.main.routing.RoutingUsers
import org.kodein.di.DI
import org.kodein.di.instance

fun Application.configureRouting(di: DI) = routing {
    configureCreateUserRouting(di)
    configureUserByIdRouting(di)
}

private fun Route.configureCreateUserRouting(di: DI) {
    val createUserRouting by di.instance<RoutingUsers>()
    createUserRouting.createUser(this)
}

private fun Route.configureUserByIdRouting(di: DI) {
    val getRoutingUserById by di.instance<RoutingUser>()
    getRoutingUserById.getUserById(route = this)
}