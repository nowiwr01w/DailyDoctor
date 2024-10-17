package nowiwr01p.daily.doctor.base_api_client.api.engine

import io.ktor.client.engine.cio.CIO

actual fun getKtorEngine() = CIO.create()