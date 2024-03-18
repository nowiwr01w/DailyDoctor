package platform.ktor

import io.ktor.client.engine.darwin.Darwin

actual fun getKtorEngine() = Darwin.create()