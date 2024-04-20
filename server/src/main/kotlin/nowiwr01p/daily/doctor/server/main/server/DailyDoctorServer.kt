package nowiwr01p.daily.doctor.server.main.server

import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import nowiwr01p.daily.doctor.database.connectDatabase
import nowiwr01p.daily.doctor.database.testTransaction
import nowiwr01p.daily.doctor.server.main.server.plugins.configureCookies
import nowiwr01p.daily.doctor.server.main.server.plugins.configureHeaders
import nowiwr01p.daily.doctor.server.main.server.plugins.configureLogging
import nowiwr01p.daily.doctor.server.main.server.plugins.configureRouting
import nowiwr01p.daily.doctor.server.main.server.plugins.configureSerialization
import org.kodein.di.DI
import org.kodein.di.DIAware

class DailyDoctorServer(override val di: DI): DIAware {
    init {
        connectDatabase()
        testTransaction()
        connectServer()
    }
}

private fun connectServer() = embeddedServer(
    factory = Netty,
    port = 8080,
    module = Application::setApplicationModule
).start(wait = true)

private fun Application.setApplicationModule() {
    configureRouting()
    configureLogging()
    configureSerialization()
//    configureHttps()
    configureHeaders()
    configureCookies()
}