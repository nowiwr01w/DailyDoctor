package nowiwr01p.daily.doctor.server.main

import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import nowiwr01p.daily.doctor.database.connectDatabase
import nowiwr01p.daily.doctor.server.di.serverModules
import nowiwr01p.daily.doctor.server.main.plugins.configureAuthentication
import nowiwr01p.daily.doctor.server.main.plugins.configureCookies
import nowiwr01p.daily.doctor.server.main.plugins.configureHeaders
import nowiwr01p.daily.doctor.server.main.plugins.configureLogging
import nowiwr01p.daily.doctor.server.main.plugins.configureRouting
import nowiwr01p.daily.doctor.server.main.plugins.configureSerialization
import nowiwr01p.daily.doctor.server.works.scheduleServerWorks
import org.kodein.di.DI
import org.kodein.di.DIAware

fun main() {
    DailyDoctorServer(serverModules)
}

class DailyDoctorServer(override val di: DI): DIAware {
    init {
        connectDatabase()
        scheduleServerWorks(di)
        connectServer(di)
    }
}

/**
 * CONNECT SERVER
 */
private fun connectServer(di: DI) = embeddedServer(
    factory = Netty,
    port = 8080,
    module = { setApplicationModule(di) }
).start(wait = true)

/**
 * SERVER MODULES
 */
private fun Application.setApplicationModule(di: DI) {
    configureRouting(di)
    configureAuthentication(di)
    configureCookies()
    configureHeaders()
    configureLogging()
    configureSerialization()
//    configureHttps()
}