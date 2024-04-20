package nowiwr01p.daily.doctor.server.main.server.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.UserIdPrincipal
import io.ktor.server.sessions.SessionStorageMemory
import io.ktor.server.sessions.Sessions
import io.ktor.server.sessions.cookie

fun Application.configureCookies() = install(Sessions) {
    cookie<UserIdPrincipal>(
        "authCookies",
        storage = SessionStorageMemory()
    ) {
        cookie.path = "/"
        cookie.extensions["SameSite"] = "lax"
    }
}