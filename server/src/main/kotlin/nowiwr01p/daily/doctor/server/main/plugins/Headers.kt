package nowiwr01p.daily.doctor.server.main.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.cachingheaders.CachingHeaders
import io.ktor.server.plugins.compression.Compression
import io.ktor.server.plugins.defaultheaders.DefaultHeaders
import io.ktor.server.plugins.forwardedheaders.XForwardedHeaders

fun Application.configureHeaders() {
    install(DefaultHeaders)
    install(Compression)
    install(CachingHeaders)
    install(XForwardedHeaders)
}