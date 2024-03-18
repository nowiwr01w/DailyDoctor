package platform.ktor

import io.ktor.client.engine.cio.CIO

actual fun getKtorEngine() = CIO.create()