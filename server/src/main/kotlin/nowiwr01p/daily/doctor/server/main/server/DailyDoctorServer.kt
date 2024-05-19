package nowiwr01p.daily.doctor.server.main.server

import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import nowiwr01p.daily.doctor.database.DailyDoctorDatabase
import nowiwr01p.daily.doctor.server.main.server.plugins.configureAuthentication
import nowiwr01p.daily.doctor.server.main.server.plugins.configureCookies
import nowiwr01p.daily.doctor.server.main.server.plugins.configureHeaders
import nowiwr01p.daily.doctor.server.main.server.plugins.configureLogging
import nowiwr01p.daily.doctor.server.main.server.plugins.configureRouting
import nowiwr01p.daily.doctor.server.main.server.plugins.configureSerialization
import nowiwr01p.daily.doctor.server.main.work.executeServerWork
import org.kodein.di.DI
import org.kodein.di.DIAware

class DailyDoctorServer(override val di: DI): DIAware {
    init {
        connectDatabase(di)
        executeServerWork(di)
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

/**
 * CONNECT DATABASE
 */
private fun connectDatabase(di: DI) = DailyDoctorDatabase(di).connect()