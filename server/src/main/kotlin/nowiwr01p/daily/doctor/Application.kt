package nowiwr01p.daily.doctor

import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import nowiwr01p.daily.doctor.plugins.configureCookies
import nowiwr01p.daily.doctor.plugins.configureHeaders
import nowiwr01p.daily.doctor.plugins.configureLogging
import nowiwr01p.daily.doctor.plugins.configureRouting
import nowiwr01p.daily.doctor.plugins.configureSerialization

fun main() {
    val server = embeddedServer(
        factory = Netty,
        port = 8080,
        module = Application::setApplicationModule
    )
    server.start(wait = true)
}

private fun Application.setApplicationModule() {
    configureRouting()
    configureLogging()
    configureSerialization()
//    configureHttps()
    configureHeaders()
    configureCookies()
}