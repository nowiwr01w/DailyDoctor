package nowiwr01p.daily.doctor.server.main.server.plugins

import io.ktor.server.application.Application
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import kotlinx.serialization.Serializable

fun Application.configureRouting() = routing {
    get("/user") {
        val user = User(228, "Huj")
        call.respond(user)
    }
}

@Serializable
data class User(
    val id: Int,
    val name: String
)