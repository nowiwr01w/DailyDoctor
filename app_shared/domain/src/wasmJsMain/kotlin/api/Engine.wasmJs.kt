package api

import io.ktor.client.engine.js.Js

actual fun getKtorEngine() = Js.create()