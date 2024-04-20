package nowiwr01p.daily.doctor.server.main.server.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.hsts.HSTS
import io.ktor.server.plugins.httpsredirect.HttpsRedirect

fun Application.configureHttps() {
    install(HttpsRedirect) {
        sslPort = 8080
    }
    install(HSTS) {
        includeSubDomains = true
    }
}