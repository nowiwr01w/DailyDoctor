package nowiwr01p.daily.doctor.server.main

import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import nowiwr01p.daily.doctor.database.connectDatabase
import nowiwr01p.daily.doctor.server.main.plugins.configureAuthentication
import nowiwr01p.daily.doctor.server.main.plugins.configureCookies
import nowiwr01p.daily.doctor.server.main.plugins.configureHeaders
import nowiwr01p.daily.doctor.server.main.plugins.configureKoin
import nowiwr01p.daily.doctor.server.main.plugins.configureLogging
import nowiwr01p.daily.doctor.server.main.plugins.configureRouting
import nowiwr01p.daily.doctor.server.main.plugins.configureSerialization
import nowiwr01p.daily.doctor.server.works.scheduleServerWorks

fun main() {
    connectDatabase()
    connectServer()
}

/**
 * CONNECT SERVER
 */
private fun connectServer() = embeddedServer(
    factory = Netty,
    port = 8080,
    module = {
        setApplicationModule()
        scheduleServerWorks()
    }
).start(wait = true)

/**
 * SERVER MODULES
 */
fun Application.setApplicationModule() {
    configureKoin()
    configureRouting()
    configureAuthentication()
    configureCookies()
    configureHeaders()
    configureLogging()
    configureSerialization()
//    configureHttps()
}