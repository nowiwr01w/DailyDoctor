package nowiwr01p.daily.doctor.base_api_client.api.engine

import io.ktor.client.engine.js.Js

actual fun getKtorEngine() = Js.create()