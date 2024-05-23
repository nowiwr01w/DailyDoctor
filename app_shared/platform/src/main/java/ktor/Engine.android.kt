package ktor

import io.ktor.client.engine.android.Android

actual fun getKtorEngine() = Android.create()