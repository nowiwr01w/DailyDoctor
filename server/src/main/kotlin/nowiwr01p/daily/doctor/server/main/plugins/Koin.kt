package nowiwr01p.daily.doctor.server.main.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import nowiwr01p.daily.doctor.server.di.serverModules
import org.koin.core.logger.Level
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

internal fun Application.configureKoin() {
    install(Koin) {
        slf4jLogger(level = Level.DEBUG)
        modules(serverModules)
    }
}