package nowiwr01p.daily.doctor.base_api_client.api.engine

import io.ktor.client.engine.android.Android

actual fun getKtorEngine() = Android.create()