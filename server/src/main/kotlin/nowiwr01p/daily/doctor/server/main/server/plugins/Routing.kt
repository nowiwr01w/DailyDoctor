package nowiwr01p.daily.doctor.server.main.server.plugins

import io.ktor.server.application.Application
import io.ktor.server.routing.Route
import io.ktor.server.routing.routing
import nowiwr01p.daily.doctor.server.main.routing.RoutingAuth
import org.kodein.di.DI
import org.kodein.di.instance

fun Application.configureRouting(di: DI) = routing {
    configureAuthRouting(di)
}

private fun Route.configureAuthRouting(di: DI) {
    val createUserRouting by di.instance<RoutingAuth>()
    createUserRouting.signIn(this)
    createUserRouting.signUp(this)
}